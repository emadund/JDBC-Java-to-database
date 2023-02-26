package model;

/**
 *
 * @author Marko Dunda
 */
public abstract class PoslovniPartner {
    
    private Long partnerID;
    private String adresa;
    private City mesto;

    public PoslovniPartner(Long partnerID, String adresa, City mesto) {
        this.partnerID = partnerID;
        this.adresa = adresa;
        this.mesto = mesto;
    }

    public PoslovniPartner() {
    }

    public Long getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(Long partnerID) {
        this.partnerID = partnerID;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public City getMesto() {
        return mesto;
    }

    public void setMesto(City mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return "PoslovniPartner{" + "partnerID=" + partnerID + ", adresa=" + adresa + ", mesto=" + mesto + '}';
    }
    
    
    
    
}
