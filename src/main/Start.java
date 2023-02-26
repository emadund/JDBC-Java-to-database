package main;

import db.DataBaseAccess;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FizickoLice;
import model.City;
import model.Narudzbenica;
import model.PoslovniPartner;
import model.PravnoLice;
import model.Proizvod;
import model.StavkaNarudzbenice;

/**
 *
 * @author Marko Dunda
 */
public class Start {
    
    public static void main(String[] args) throws ParseException {
        
        City mesto1 = new City("85330", "Kotor");
        City mesto2 = new City("11000", "Beograd");
        PoslovniPartner p1 = new PravnoLice("12345678","123456789", "Aroma", 1L, "Senjacka bb", mesto1);
        PoslovniPartner p2 = new FizickoLice("123454321234", "Pera Peric", 2L, "Sarajevska 41", mesto2);
        
        StavkaNarudzbenice s1 = new StavkaNarudzbenice();
        s1.setRb(1L);
        s1.setKolicina(5);
        s1.setProizvod(new Proizvod(1L, "Crni hleb", new BigDecimal(80)));
        
        StavkaNarudzbenice s2 = new StavkaNarudzbenice();
        s2.setRb(2L);
        s2.setKolicina(3);
        s2.setProizvod(new Proizvod(3L, "Bonzita", new BigDecimal(50)));
        
        List<StavkaNarudzbenice> stavke  = new ArrayList<>();
        stavke.add(s1);
        stavke.add(s2);
        
        Narudzbenica narudzbenica1 =new Narudzbenica(null, new SimpleDateFormat("dd.MM.yyyy").parse("15.12.2022"), p1, stavke);
        
        for (StavkaNarudzbenice stavkaNarudzbenice : stavke) {
            stavkaNarudzbenice.setNarudzbenica(narudzbenica1);
        }
        DataBaseAccess db = new DataBaseAccess();
        
        try {
            db.uspostavljanjeKonekcijeSaBazom();
            
            try {
                db.insertNarudzbenica(narudzbenica1);
                db.potvrdiTransakciju();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                db.ponistiTransakciju();
            }
            /*
            
            
            try {
            db.insertPoslovniPartner(p1);
            db.potvrdiTransakciju();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                db.ponistiTransakciju();                       
            }
         
        try {            
            db.insertPoslovniPartner(p2);
            db.potvrdiTransakciju();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            db.ponistiTransakciju();        }   
    } */ }
        catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());                                      
            }
        finally {
            try {
                db.raskidanjeKonekcijeSaBazom();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());                                       
            }
        } 
}
    /*
    public static void radSaDatumom(){
        Date datum = new Date();
        System.out.println(""+datum);
        
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        System.out.println("Datum: "+formater.format(datum));
        
        SimpleDateFormat formater2 = new SimpleDateFormat("dd.MM.yyyy");
        Date mojDatum;
        try {
            mojDatum = formater2.parse("08.07.1980");
            System.out.println("Datum: "+mojDatum);
        } catch (ParseException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        long datumKaoLong = datum.getTime();
        java.sql.Date sqlDatum=new java.sql.Date(datumKaoLong);
    } */
}
