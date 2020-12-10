package pl.documents.model.enums;

/**
 * Rodzaj adresu
 */
public enum  AddressType
{
    /**
     * Zamieszkania, zameldowania i do korespondencji
     */
    ALL,
    /**
     * Zamieszkania
     */
    RESIDENCE,
    /**
     * Zameldowania
     */
    CHECKIN,
    /**
     * Do korespondencji
     */
    CORRESPONDENCE,
    /**
     * Zamieszkania i zameldowania
     */
    RESIDENCE_CHECKIN,
    /**
     * Zamieszkania i do korespondencji
     */
    RESIDENCE_CORRESPONDENCE,
    /**
     * Zameldowania i do korespondencji
     */
    CHECKIN_CORRESPONDENCE
}
