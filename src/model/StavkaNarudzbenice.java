package model;

/**
 *
 * @author Marko Dunda
 */
public class StavkaNarudzbenice {
    
    private Narudzbenica narudzbenica;
    private Long rb;
    int kolicina;
    Proizvod proizvod;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, Long rb, int kolicina, Proizvod proizvod) {
        this.narudzbenica = narudzbenica;
        this.rb = rb;
        this.kolicina = kolicina;
        this.proizvod = proizvod;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public String toString() {
        return "StavkaNarudzbenice{" + ", rb=" + rb + ", kolicina=" + kolicina + ", proizvod=" + proizvod + '}';
    }
    
    
    
}
