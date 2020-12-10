package pl.documents.model.enums;

/**
 * Specjalne uprawnienia rodziców dzieci do lat czterech
 */
public enum  ChildUnderFourPermissions
{
    /**
     * 1. sprawując opiekę nad dzieckiem do lat czterech :)
     * a. wyrażam zgodę na pracę w rozkładzie czasu pracy przewidującym przedłużenie
     *  normy czasu pracy powyżej 8 godzin na dobę (art. 148 pkt 3 Kodeksu pracy)
     * b. wyrażam zgodę na pracę w godzinach nadliczbowych, w porze nocnej, w przerywanym
     *  systemie czasu pracy a także na delegowanie poza stałe miejsce pracy (art. 178 § 2 kodeksu pracy)
     */
    YES_YES,
    /**
     * 1. sprawując opiekę nad dzieckiem do lat czterech :)
     * a. wyrażam zgodę na pracę w rozkładzie czasu pracy przewidującym przedłużenie
     *  normy czasu pracy powyżej 8 godzin na dobę (art. 148 pkt 3 Kodeksu pracy)
     * b. nie wyrażam zgody na pracę w godzinach nadliczbowych, w porze nocnej, w przerywanym
     *  systemie czasu pracy a także na delegowanie poza stałe miejsce pracy (art. 178 § 2 kodeksu pracy)
     */
    YES_NO,
    /**
     * 1. sprawując opiekę nad dzieckiem do lat czterech :)
     * a. nie wyrażam zgody na pracę w rozkładzie czasu pracy przewidującym przedłużenie
     *  normy czasu pracy powyżej 8 godzin na dobę (art. 148 pkt 3 Kodeksu pracy)
     * b. wyrażam zgodę na pracę w godzinach nadliczbowych, w porze nocnej, w przerywanym
     *  systemie czasu pracy a także na delegowanie poza stałe miejsce pracy (art. 178 § 2 kodeksu pracy)
     */
    NO_YES,
    /**
     * 1. sprawując opiekę nad dzieckiem do lat czterech :)
     * a. nie wyrażam zgody na pracę w rozkładzie czasu pracy przewidującym przedłużenie
     *  normy czasu pracy powyżej 8 godzin na dobę (art. 148 pkt 3 Kodeksu pracy)
     * b. nie wyrażam zgody na pracę w godzinach nadliczbowych, w porze nocnej, w przerywanym
     *  systemie czasu pracy a także na delegowanie poza stałe miejsce pracy (art. 178 § 2 kodeksu pracy)
     */
    NO_NO
}
