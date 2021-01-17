package pl.documents.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.documents.exception.RegisterException;
import pl.documents.model.User;
import pl.documents.model.enums.UserType;
import pl.documents.model.projection.EmailAndPasswordWriteModel;
import pl.documents.repository.UserRepository;

@Service
public class EmployeeAndAdminService
{
    private final UserRepository userRepository;

    public EmployeeAndAdminService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteWorker(User user, EmailAndPasswordWriteModel writeModel) throws RegisterException
    {
        if(!user.getPassword().equals(writeModel.getPassword()))
            throw new RegisterException("The given password is incorrect!");
        User toDelete = userRepository.findByEmail(writeModel.getEmail()).orElseThrow(
                ()->new RegisterException("There is no user with this e-mail!")
        );
        if(!toDelete.getUserType().equals(UserType.WORKER))
            throw new RegisterException("Cannot delete user of type " + toDelete.getUserType());
        userRepository.deleteById(toDelete.getId());
    }
    @Transactional
    public void deleteHR(User user, EmailAndPasswordWriteModel writeModel) throws RegisterException
    {
        if(!user.getPassword().equals(writeModel.getPassword()))
            throw new RegisterException("The given password is incorrect!");
        User toDelete = userRepository.findByEmail(writeModel.getEmail()).orElseThrow(
                ()->new RegisterException("There is no user with this e-mail!")
        );
        if(!toDelete.getUserType().equals(UserType.HR_EMPLOYEE))
            throw new RegisterException("Cannot delete user of type " + toDelete.getUserType());
        userRepository.deleteById(toDelete.getId());
    }
}
