package model;

/**
 *
 * @author Marko Dunda
 */
public class PravnoLice extends PoslovniPartner {
    
    private String pib;
    private String maticniBroj;
    private String naziv;

    public PravnoLice() {
    }

    public PravnoLice(String pib, String maticniBroj, String naziv, Long partnerID, String adresa, City mesto) {
        super(partnerID, adresa, mesto);
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    

    @Override
    public String toString() {
        return super.toString()+"PravnoLice{" + "pib=" + pib + ", maticniBroj=" + maticniBroj + ", naziv=" + naziv + '}';
    }

    
    
    
    
    
}
