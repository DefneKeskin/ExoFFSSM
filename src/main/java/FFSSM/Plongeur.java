package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne {

    public int niveau;
    public GroupeSanguin gp;
    public ArrayList<Licence> licences;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niveau, GroupeSanguin gp) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        licences = new ArrayList<Licence>();
        this.niveau = niveau;
        this.gp = gp;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public String getNumeroINSEE() {
        return numeroINSEE;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public LocalDate getNaissance() {
        return naissance;
    }

    public GroupeSanguin getGp() {
        return gp;
    }

    public void ajouteLicence(String numero, LocalDate delivrance, Club c) {
        licences.add(new Licence(this, numero, delivrance, c));
    }

    public Licence derniereLicence() {
        int taille = licences.size();
        return (licences.get(taille - 1));
    }

}
