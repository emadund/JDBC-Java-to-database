package db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.FizickoLice;
import model.City;
import model.Narudzbenica;
import model.PoslovniPartner;
import model.PravnoLice;
import model.Proizvod;
import model.StavkaNarudzbenice;

import util.DbUtil;

/**
 *
 * @author Cartman
 */
public class DataBaseAccess {
    private Connection connection = null;

    public void uspostavljanjeKonekcijeSaBazom() throws Exception {
        DbUtil util = new DbUtil();
        try {
            connection = DriverManager.getConnection(util.getURL(), util.getUser(), util.getPassword());
            connection.setAutoCommit(false);//omogucava upravljanje transakcijama
            System.out.println("Uspesno povezivanje sa bazom");
        } catch (SQLException ex) {
            System.out.println("Greska: Povezivanje nije uspelo");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom pristupa bazi podataka, pokusajte kasnije.\n" + ex.getMessage());
        }
    }

    public void raskidanjeKonekcijeSaBazom() throws Exception {
        //Connection connection = null;
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija je sa bazom je raskinuta");
            }

        } catch (SQLException ex) {
            System.out.println("Greska: Konekcija sa bazom nije raskinuta");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom raskidanja konekcije na bazu!\n" + ex.getMessage());
        }
    }
    
    public void potvrdiTransakciju() throws Exception{
        try {
            connection.commit();
            System.out.println("Uspesno potvrdjena tranakcija");
        } catch (SQLException ex) {
            System.out.println("Greska: Transakcija nije potvrdjena");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom potvrdjivanja transakcije!\n" + ex.getMessage());
        }
    }
    
    public void ponistiTransakciju() throws Exception{
        try {
            connection.rollback();
            System.out.println("Uspesno ponistena tranakcija");
        } catch (SQLException ex) {
            System.out.println("Greska: Transakcija nije ponistena");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom ponistavanja transakcije!\n" + ex.getMessage());
        }
    }
    
         public void insertProizvod(Proizvod proizvod) throws Exception {

        String upit = "INSERT INTO proizvod (naziv, cena) VALUES ('" + proizvod.getNaziv() + "'," + proizvod.getCena() + ");";
        System.out.println("Upit: " + upit);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

            ResultSet rsID = statement.getGeneratedKeys();
            if (rsID.next()) {
                proizvod.setProizvodId(rsID.getLong(1));
            }
            System.out.println("Proizvod je uspesno dodat i njegov ID je " + proizvod.getProizvodId());
            rsID.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije uspesno dodat");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom dodavanja novog proizvoda!\n" + ex.getMessage());
        }
    }

    public void insertProizvodBoljiNacin(Proizvod proizvod) throws Exception {

        //String query = "INSERT INTO proizvod (naziv, cena) VALUES ('" + proizvod.getNaziv() + "',"+proizvod.getCena()+");";
        String upit = "INSERT INTO proizvod (naziv, cena) VALUES (?,?);";
        System.out.println("Upit: " + upit);

        try {
            PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, proizvod.getNaziv());
            statement.setBigDecimal(2, proizvod.getCena());

            statement.executeUpdate();

            ResultSet rsID = statement.getGeneratedKeys();
            if (rsID.next()) {
                proizvod.setProizvodId(rsID.getLong(1));
            }
            System.out.println("Proizvod je uspesno dodat i njegov ID je " + proizvod.getProizvodId());
            rsID.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije uspesno dodat");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom dodavanja novog proizvoda!\n" + ex.getMessage());
        }
    }

    public void insertProizvodi(List<Proizvod> proizvodi) throws Exception {
        String upit = "INSERT INTO proizvod (naziv, cena) VALUES (?,?);";
        System.out.println("Upit: " + upit);

        try {
            PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            for (Proizvod proizvod : proizvodi) {
                statement.setString(1, proizvod.getNaziv());
                statement.setBigDecimal(2, proizvod.getCena());

                statement.executeUpdate();

                ResultSet rsID = statement.getGeneratedKeys();
                if (rsID.next()) {
                    proizvod.setProizvodId(rsID.getLong(1));
                }
                System.out.println("Proizvod je uspesno dodat i njegov ID je " + proizvod.getProizvodId());
                rsID.close();
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije uspesno dodat");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom dodavanja novih proizvoda!\n" + ex.getMessage());
        }
    }

    public void insertMesto(City mesto) {
        String upit = "INSERT INTO mesto (ptt, naziv) VALUES ('" + mesto.getZipCode() + "','" + mesto.getName() + "')";
        System.out.println("Upit: " + upit);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            System.out.println("Mesto je uspesno dodato");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Mesto nije uspesno dodato");
            ex.printStackTrace();
        }
    }

    public void deleteProizvod(Proizvod proizvod) {

        String upit = "DELETE FROM proizvod WHERE proizvodId = " + proizvod.getProizvodId();
        System.out.println("Upit: " + upit);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            System.out.println("Proizvod je uspesno obrisan iz baze");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije uspesno obrisan");
            ex.printStackTrace();
        }

    }

    public void deleteMesto(City mesto) {

        String upit = "DELETE FROM mesto WHERE ptt = " + mesto.getZipCode();
        System.out.println("Upit: " + upit);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            System.out.println("Mesto je uspesno obrisano iz baze");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Mesto nije uspesno obrisano");
            ex.printStackTrace();
        }

    }

    public void updateProizvod(Proizvod proizvod) throws Exception {
        String upit = "UPDATE proizvod SET naziv = '" + proizvod.getNaziv() + "' , cena = " + proizvod.getCena() + " WHERE proizvodId = " + proizvod.getProizvodId();
        System.out.println("Upit: " + upit);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            System.out.println("Uspesno izmenjen proizvod");
            statement.close();

        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije izmenjen");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom izmene proizvoda!\n" + ex.getMessage());
        }
    }

    public void updateMesto(City mesto) {
        String upit = "UPDATE mesto SET naziv = '" + mesto.getName() + "' WHERE ptt = '" + mesto.getZipCode() + "'";
        System.out.println("Upit: " + upit);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            System.out.println("Uspesno izmenjeno mesto");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Mesto nije izmenjeno");
            ex.printStackTrace();
        }
    }

    public List<Proizvod> getAllProizvod() throws Exception {
        List<Proizvod> proizvodi = new ArrayList<>();
        String upit = "SELECT proizvodId, naziv, cena FROM proizvod";
        System.out.println("Upit: " + upit);

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Proizvod proizvod = new Proizvod();
                proizvod.setProizvodId(rs.getLong("proizvodId"));
                proizvod.setNaziv(rs.getString("naziv"));
                proizvod.setCena(rs.getBigDecimal("cena"));
                proizvodi.add(proizvod);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvodi nisu ucitani!");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom ucitavanja proizvoda iz baze!\n" + ex.getMessage());
        }
        return proizvodi;
    }
    
    public BigDecimal findProizvod(String naziv) {
        String upit = "SELECT Naziv, cena FROM proizvod WHERE naziv=?;";
        System.out.println("Upit: " + upit);
        BigDecimal temp = new BigDecimal(0.0);
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setString(1,upit);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
                temp= BigDecimal.valueOf((rs.getFloat(1)));               
                // ne znam zasto
            }
            rs.close();
            statement.close();
            
        } catch (SQLException ex) {
            System.out.println("Greska: Proizvod nije pronadjen");
            ex.printStackTrace();
            
        }
        return temp;
        
    }
    
    public String findNaziv(String ptt) {
        String query = "SELECT naziv FROM mesto WHERE ptt=?;";
        System.out.println("Upit: " + query);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,ptt);
            ResultSet rs = statement.executeQuery();
            String temp=null;
            if (rs.next()){
            temp=rs.getString(1);}
            rs.close();
            statement.close();
            return temp;
        } catch (SQLException ex) {
            System.out.println("Greska: Mesta nisu ucitana");
            ex.printStackTrace();
            return null;
        }
        
        
    }

    
    
    public void insertPoslovniPartner (PoslovniPartner partner) throws Exception {
        
        String upit="INSERT INTO poslovnipartner (partnerID, adresa, ptt) VALUES (?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setLong(1, partner.getPartnerID());
            statement.setString(2, partner.getAdresa());
            statement.setString(3, partner.getMesto().getZipCode());
            statement.executeUpdate();
            statement.close();
            
            if (partner instanceof FizickoLice) {
                insertFizickoLice((FizickoLice)partner);
                
            } else {
                insertPravnoLice((PravnoLice)partner);
                
            }
            System.out.println("Uspesno kreiran poslovni partner");
        } catch (SQLException ex) {
            System.err.println("Greska: Nije kreiran poslovni partner!");
            ex.printStackTrace();
            throw new Exception ("Dogodila se greska prilikom kreiranja poslovnog partnera");
        }
        
        
    }
    
    private void insertFizickoLice (FizickoLice fl) throws Exception {
        String upit="INSERT INTO fizickolice (partnerID, JMBG, imeprezime) VALUES (?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setLong(1, fl.getPartnerID());
            statement.setString(2, fl.getJmbg());
            statement.setString(3, fl.getImePrezime());
            statement.executeUpdate();
            statement.close();           
            
            System.out.println("Uspesno kreirano fizicko lice");
        } catch (SQLException ex) {
            System.err.println("Greska: Nije kreirano fizicko lice!");
            ex.printStackTrace();
            throw new Exception ("Dogodila se greska prilikom kreiranja fizickog lica");
        }
        
        
    }
    
    private void insertPravnoLice (PravnoLice pl) throws Exception {
        String upit="INSERT INTO pravnolice (partnerID, maticnibroj, pib, naziv) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setLong(1, pl.getPartnerID());
            statement.setString(2, pl.getMaticniBroj());
            statement.setString(3, pl.getPib());
            statement.setString(4, pl.getNaziv());
            statement.executeUpdate();
            statement.close();           
            
            System.out.println("Uspesno kreirano pravno lice");
        } catch (SQLException ex) {
            System.err.println("Greska: Nije kreirano pravno lice!");
            ex.printStackTrace();
            throw new Exception ("Dogodila se greska prilikom kreiranja pravnog lica");
        }
        
        
    }
    
    public void insertNarudzbenica (Narudzbenica narudzbenica) throws Exception {
        String upit="INSERT INTO narudzbenica (datum, partnerID) VALUES (?,?);";
        System.out.println(upit);
        
        try {
            PreparedStatement statement=connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new java.sql.Date(narudzbenica.getDatum().getTime()));
            statement.setLong(2, narudzbenica.getPartner().getPartnerID());
            
            statement.executeUpdate();
            
            ResultSet rsID = statement.getGeneratedKeys();
            if (rsID.next()) {
                narudzbenica.setBrojNarudzbenice(rsID.getLong(1));
            }
             statement.close();
             rsID.close();            
            for (StavkaNarudzbenice stavka: narudzbenica.getStavke()) {
                insertStavkaNarudzbenica(stavka);
            }
            
            System.out.println("Uspesno kreirana narudzbenica");
        }
        catch (SQLException ex) {
            System.out.println("Greska: Nije kreirana narudzbenica!");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom kreiranja narudzbenice!\n"+ex.getMessage());
        }
    }
      private void insertStavkaNarudzbenica (StavkaNarudzbenice stavka) throws Exception {
        String upit="INSERT INTO stavkanarudzbenice (brojNarudzbenice, rb, kolicina, proizvodID) VALUES (?,?,?,?);";
        System.out.println(upit);
        
        try {
            PreparedStatement statement=connection.prepareStatement(upit);
            statement.setLong(1, stavka.getNarudzbenica().getBrojNarudzbenice());
            statement.setLong(2, stavka.getRb());
            statement.setInt(3, stavka.getKolicina());
            statement.setLong(4, stavka.getProizvod().getProizvodId());
            
            statement.executeUpdate();
            statement.close();
            
            System.out.println("Uspesno kreirana stavka narudzbenice!");
        }
        catch (SQLException ex) {
            System.out.println("Greska: Nije kreirana stavka narudzbenice!");
            ex.printStackTrace();
            throw new Exception("Dogodila se greska prilikom kreiranja stavke narudzbenice!\n"+ex.getMessage());
        }
    }
    
       
}
