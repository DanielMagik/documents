package pl.documents.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.documents.exception.AddressTypeExistsException;
import pl.documents.exception.BadAddressException;
import pl.documents.exception.BadIdException;
import pl.documents.logic.DataChecker;
import pl.documents.model.Address;
import pl.documents.model.projection.AddressReadModel;
import pl.documents.model.projection.AddressWriteModel;
import pl.documents.service.AddressService;
import pl.documents.service.WorkerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AddressController
{
    private final AddressService addressService;
    private final WorkerService workerService;
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    public AddressController(final AddressService addressService, final WorkerService workerService)
    {
        this.addressService = addressService;
        this.workerService = workerService;
    }
    /**
     * Odczyt adresów pracownika o zadanym id
     * @param id id pracownika
     * @return lista z przebiegiem wykształcenia
     */
    @GetMapping(value = "/workers/address/{id}")
    ResponseEntity<List<AddressReadModel>> readWorkerAddresses(@PathVariable UUID id)
    {
        List<AddressReadModel> result;
        try
        {
            result = workerService.readWorkerAddresses(id).stream()
                    .map(AddressReadModel::new).collect(Collectors.toList());
            logger.info("Read addresses from worker with id "+id+"!");
        }
        catch (BadIdException e)
        {
            logger.info("Read addresses from worker with id "+id+"!Worker not Found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * Dodanie adresu do pracownika o zadanym id bądź aktualizacja adresu
     * @param id id pracownika
     * @param addressWriteModel adres
     * @return informacja o pomyślnym bądź nieudanym dodaniu
     */
    @PostMapping("/workers/address/{id}")
    ResponseEntity<?> addWorkerAddress(@PathVariable UUID id, @RequestBody AddressWriteModel addressWriteModel)
    {
        try
        {
            Address toUpdate = addressWriteModel.toAddress();
            List<AddressReadModel> addressList = workerService.readWorkerAddresses(id).stream()
                    .map(AddressReadModel::new).collect(Collectors.toList());;
            DataChecker.checkAddressCorrectness(toUpdate);
            addressService.checkTypeExists(toUpdate, addressList);
            workerService.addAddress(id,toUpdate);
            logger.info("Add address to worker with id " + id+ " successful!");
        }
        catch (AddressTypeExistsException e)
        {
            logger.info("Add address to worker with id " + id+ ". Old addresses deleted!");
            return updateWorkerAddress(id,addressWriteModel);
        }
        catch (BadAddressException e)
        {
            logger.info("Add address to worker with id " + id+ ". Bad address");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        catch (BadIdException e)
        {
            logger.info("Update address to worker with id " + id+ ". Worker not found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    ResponseEntity<?> updateWorkerAddress(UUID id, AddressWriteModel addressWriteModel)
    {
        Address toUpdate = addressWriteModel.toAddress();
        try
        {
            List<Address> addressList = workerService.readWorkerAddresses(id);
            DataChecker.checkAddressCorrectness(toUpdate);
            List<Address> newAddresses = addressService.updateAddress(toUpdate,addressList);

            for(Address a : newAddresses)
            {
                workerService.addAddress(id,a);
            }
            logger.info("Update address to worker with id " + id+ " successful!");
        }
        catch (BadAddressException e)
        {
            logger.info("Update address to worker with id " + id+ ". Bad address");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
        }
        catch (BadIdException e)
        {
            logger.info("Update address to worker with id " + id+ ". Worker not found!");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
