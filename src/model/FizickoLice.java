package model;

/**
 *
 * @author Marko Dunda
 */
public class FizickoLice extends PoslovniPartner{
    
    private String jmbg;
    private String imePrezime;

    public FizickoLice(String jmbg, String imePrezime, Long partnerID, String adresa, City mesto) {
        super(partnerID, adresa, mesto);
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
    }

    public FizickoLice() {
       
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }
    
    

    @Override
    public String toString() {
        return super.toString()+"FizickoLice{" + "jmbg=" + jmbg + ", imePrezime=" + imePrezime + '}';
    }
    
    
    
    
}
