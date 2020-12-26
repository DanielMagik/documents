package pl.documents.logic;

import pl.documents.exception.BadAddressException;
import pl.documents.model.Address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataChecker
{
    public static boolean compareStrings(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }
    public static void checkAddressCorrectness(Address address) throws BadAddressException
    {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("\\d{2}-\\d{3}");
        matcher = pattern.matcher(address.getPostalCode());
        if(!matcher.matches())
        {
            throw new BadAddressException("Enter a correct postal code!");
        }
        pattern=Pattern.compile("\\d{1,}\\w{0,1}");
        matcher=pattern.matcher(address.getHomeNumber());
        if(!matcher.matches())
        {
            throw  new BadAddressException("Enter a correct home number!");
        }
        if(address.getFlatNumber()!=null)
        {
            matcher = pattern.matcher(address.getFlatNumber());
            if(!matcher.matches())
            {
                throw new BadAddressException("Enter a correct flat number!");
            }
        }
    }

}
