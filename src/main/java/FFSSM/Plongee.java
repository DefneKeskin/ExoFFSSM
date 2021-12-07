/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Plongee {

    public Site lieu;

    public Moniteur chefDePalanquee;

    public LocalDate date;

    public int profondeur;

    public int duree;

    public ArrayList<Plongeur> plongeurs;

    //il faut memoriser les licenses 
    public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        plongeurs = new ArrayList<Plongeur>();
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }

    public Plongee(Moniteur chefDePalanquee) {
        this.chefDePalanquee = chefDePalanquee;
    }
    /*
    public ArrayList<Plongeur> getPlongeurs() {
        return plongeurs;
    }*/

    public void ajouteParticipant(Plongeur participant) {
        plongeurs.add(participant);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Détermine si la plongée est conforme. Une plongée est conforme si tous
     * les plongeurs de la palanquée ont une licence valide à la date de la
     * plongée
     *
     * @return vrai si la plongée est conforme
     */
    public boolean estConforme() {
        // TODO: Implémenter cette méthode
        boolean conforme = true;
        for (Plongeur p : plongeurs) {
            if (p.derniereLicence().estValide(date) == false) {
                conforme = false;
            }
        }
        return conforme;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.lieu);
        hash = 97 * hash + Objects.hashCode(this.chefDePalanquee);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.profondeur;
        hash = 97 * hash + this.duree;
        hash = 97 * hash + Objects.hashCode(this.plongeurs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plongee other = (Plongee) obj;
        if (this.profondeur != other.profondeur) {
            return false;
        }
        if (this.duree != other.duree) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.chefDePalanquee, other.chefDePalanquee)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.plongeurs, other.plongeurs)) {
            return false;
        }
        return true;
    }

}
