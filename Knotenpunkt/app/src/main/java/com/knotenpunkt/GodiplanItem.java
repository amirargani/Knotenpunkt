package com.knotenpunkt;

public class GodiplanItem {
    private String Datum;
    private String Fr_Sabbat;
    private String Moderation;
    private String KidsGo;
    //private String Musik;
    private String Gespreachsltng;
    private String Predigt;
    private String Kindermoment;
    private String Zeit;
    private String Ort;
    private String Putzdienst;

    //String Musik,
    public GodiplanItem(String Datum, String Fr_Sabbat, String Moderation, String KidsGo, String Gespreachsltng, String Predigt, String Kindermoment, String Zeit, String Ort, String Putzdienst) {
        this.Datum = Datum;
        this.Fr_Sabbat = Fr_Sabbat;
        this.Moderation = Moderation;
        this.KidsGo = KidsGo;
        //this.Musik = Musik;
        this.Gespreachsltng = Gespreachsltng;
        this.Predigt = Predigt;
        this.Kindermoment = Kindermoment;
        this.Zeit = Zeit;
        this.Ort = Ort;
        this.Putzdienst = Putzdienst;
    }

    public String getDatum() {
        return Datum;
    }

    public String getFr_Sabbat() {
        return Fr_Sabbat;
    }

    public String getModeration() {
        return Moderation;
    }

    public String getKidsGo() {
        return KidsGo;
    }

//    public String getMusik() { return Musik; }

    public String getGespreachsltng() {
        return Gespreachsltng;
    }

    public String getPredigt() {
        return Predigt;
    }

    public String getKindermoment() {
        return Kindermoment;
    }

    public String getZeit() {
        return Zeit;
    }

    public String getOrt() {
        return Ort;
    }

    public String getPutzdienst() { return Putzdienst; }
}
