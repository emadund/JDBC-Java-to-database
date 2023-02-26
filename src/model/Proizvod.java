package model;

import java.math.BigDecimal;

/**
 *
 * @author Nikola Maksimovic
 */
public class Proizvod {
    
    private Long proizvodId;
    private String naziv;
    private BigDecimal cena;

    public Proizvod(){
    }

    public Proizvod(Long proizvodId, String naziv, BigDecimal cena) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
    }
    
   

    public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
    
    @Override
    public String toString() {
        return "Proizvod{" + "proizvodId=" + proizvodId + ", naziv=" + naziv + ", cena=" + cena + '}';
    }
    
}
