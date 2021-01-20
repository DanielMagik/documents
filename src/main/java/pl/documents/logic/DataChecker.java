package pl.documents.logic;

public class DataChecker
{
    public static boolean compareStrings(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

}
