package com.knotenpunkt;

public class CalendarItem {
    private String Datum;
    private String Zeit;
    private String Titel;
    private String Beschreibung;
    private String Ort;

    public CalendarItem(String Datum, String Zeit, String Titel, String Beschreibung, String Ort) {
        this.Datum = Datum;
        this.Zeit = Zeit;
        this.Titel = Titel;
        this.Beschreibung = Beschreibung;
        this.Ort = Ort;
    }

    public String getDatum() {
        return Datum;
    }

    public String getZeit() {
        return Zeit;
    }

    public String getTitel() {
        return Titel;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public String getOrt() {
        return Ort;
    }
}
