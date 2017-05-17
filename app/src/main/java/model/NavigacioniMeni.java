package model;

/**
 * Created by borcha on 17.05.17..
 */

public class NavigacioniMeni {


    private String naslov;
    private String opis;
    private int ikona;


    public NavigacioniMeni(String _naslov, String _opis, int _ikona){
        this.naslov=_naslov;
        this.opis=_opis;
        this.ikona=_ikona;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIkona() {
        return ikona;
    }

    public void setIkona(int ikona) {
        this.ikona = ikona;
    }
}
