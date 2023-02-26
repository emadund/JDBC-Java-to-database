package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marko Dunda
 */
public class Narudzbenica {
    
    private Long brojNarudzbenice;
    private Date datum;
    private PoslovniPartner partner;
    List<StavkaNarudzbenice> stavke;

    public Narudzbenica() {
    }

    public Narudzbenica(Long brojNarudzbenice, Date datum, PoslovniPartner partner, List<StavkaNarudzbenice> stavke) {
        this.brojNarudzbenice = brojNarudzbenice;
        this.datum = datum;
        this.partner = partner;
        this.stavke = stavke;
    }

    public Long getBrojNarudzbenice() {
        return brojNarudzbenice;
    }

    public void setBrojNarudzbenice(Long brojNarudzbenice) {
        this.brojNarudzbenice = brojNarudzbenice;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public PoslovniPartner getPartner() {
        return partner;
    }

    public void setPartner(PoslovniPartner partner) {
        this.partner = partner;
    }

    public List<StavkaNarudzbenice> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaNarudzbenice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Narudzbenica{" + "brojNarudzbenice=" + brojNarudzbenice + ", datum=" + datum + ", partner=" + partner + ", stavke=" + stavke + '}';
    }
    
    
    
}
