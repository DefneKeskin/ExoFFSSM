/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author defne
 */
public class NewEmptyJUnitTest {

    Club club1;
    Plongeur p1;
    Moniteur m1;
    Plongee plonge1,plonge3;
    Licence l1;
    Embauche e1;
    Plongee plonge2;

    /*
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }*/
    @BeforeEach
    public void setUp() {
        club1 = new Club(m1, "IC", "0788905643");
        p1 = new Plongeur("0678", "Dupont", "Lucas", "24 RUE DES ROSES", "O789096543", LocalDate.of(1999, 11, 24), 3, GroupeSanguin.APLUS);
        m1 = new Moniteur("0767", "Santhune", "Lucie", "25 RUE DES ROSES", "O789096553", LocalDate.of(1999, 10, 24), 6, GroupeSanguin.APLUS, 13);
        plonge1 = new Plongee(new Site("Lyon", "RUE DES FLEURS"), m1, LocalDate.of(2021, 12, 6), 6, 2);
        plonge3 = new Plongee(new Site("Grenoble", "RUE DES FLEURS"), m1, LocalDate.of(2021, 12, 4), 6, 2);
        l1 = new Licence(p1, "78", LocalDate.of(2019, 11, 24), club1);
        e1 = new Embauche(LocalDate.of(2021, 12, 5), m1, club1);
        plonge2 = new Plongee(m1);

    }

    //LICENCE
    @Test
    public void testEstValide() {
        //LICENCE NON VALIDE
        assertFalse(l1.estValide(LocalDate.of(2021, 11, 24)), "La licence n'est pas valide");

        //LICENCE VALIDE
        assertTrue(l1.estValide(LocalDate.of(2019, 12, 6)), "La licence est  valide");
    }

    @Test
    public void testGetPosseseur() {
        assertEquals(l1.getPossesseur(), p1, "Le posseseur doit être le plongeur p1");

    }

    //PLONGEUR
    @Test
    public void testAjouteLicence() {
        //on ajoute une licence et on verifie que la dernière ajouté à la liste soit bien celle qu'on vient d'ajouter

        p1.ajouteLicence("78", LocalDate.of(2019, 11, 24), club1);

        assertEquals("78", p1.licences.get(p1.licences.size() - 1).getNumero(), "La dernière licence ajouté doit être de numero 78");
        assertEquals(LocalDate.of(2019, 11, 24), p1.licences.get(p1.licences.size() - 1).getDelivrance(), "La dernière licence ajouté doit avoir comme date de délivrance  '2019, 11, 24'");
        assertEquals(club1, p1.licences.get(p1.licences.size() - 1).getClub(), "La dernière licence ajouté doit être le club 'club1' ");
    }

    @Test
    public void testDerniereLicence() {
        //revient à la même du dessus car ma liste de licence possède qu'une seule licence
        p1.ajouteLicence("78", LocalDate.of(2019, 11, 24), club1);
        assertEquals("78", p1.derniereLicence().getNumero(), "La dernière licence ajouté doit être de numero 78");
        assertEquals(LocalDate.of(2019, 11, 24), p1.derniereLicence().getDelivrance(), "La dernière licence ajouté doit avoir comme date de délivrance  '2019, 11, 24'");
        assertEquals(club1, p1.derniereLicence().getClub(), "La dernière licence ajouté doit être le club 'club1' ");
    }

    //MONITEUR
    @Test
    public void testTerminerEmbauche() {
        m1.embauches.add(e1);
        m1.terminerEmbauche(LocalDate.of(2021, 12, 6));

        assertEquals(LocalDate.of(2021, 12, 6), m1.embauches.get(m1.embauches.size() - 1).getFin(), "La date de fin doit etre 2021,12,6 ");

    }
    @Test
    public void testEmployeurActuelEtNouvelleEmbaucheEtEmplois() {
        //moniteur n'a pas d'employeur 
        assertEquals(m1.employeurActuel() , Optional.empty(),"Le moniteur ne doit pas avoir un employeur");
        
        //ajoute un emploie
        //veirfie nouvelle embauche 
        m1.nouvelleEmbauche(club1, LocalDate.of(2021, 12, 5));
        assertEquals(m1.employeurActuel() , Optional.ofNullable(club1),"L'employeur doit avoir un employeur");
        
        ArrayList<Embauche> e = new ArrayList<Embauche>();
        e.add(e1);
        //avece e1 = new Embauche(LocalDate.of(2021, 12, 5), m1, club1);
        //Pour verifier methode emplois
        assertEquals(m1.emplois() , e,"La liste d'embauche doit être identique");
    }
 
    //PLONGEE
    @Test
    public void testAjouteParticipant() {
        plonge1.ajouteParticipant(p1);
        //verifie si on a bien ajoute une nouvelle embauche dans la liste embauches.

        assertEquals("0678", plonge1.plongeurs.get(plonge1.plongeurs.size() - 1).getNumeroINSEE(), "Le numero INSEE doit etre 0678");
        assertEquals("Dupont", plonge1.plongeurs.get(plonge1.plongeurs.size() - 1).getNom(), "Le nom doit etre Dupont");
        //Si cela passe, pas besoin de vrifier le prenom, numero de telephone etc ...
        assertEquals(3, plonge1.plongeurs.get(plonge1.plongeurs.size() - 1).getNiveau(), "Le niveau doit etre 3");
        assertEquals(GroupeSanguin.APLUS, plonge1.plongeurs.get(plonge1.plongeurs.size() - 1).getGp(), "Le Gp doit etre A+");

    }

    /*
     @Test
     //A FAIRE
    public void testGetPlongeurs() {
        plonge1.ajouteParticipant(p1);
           assertEquals(p1, plonge1.getPlongeurs(), "Il doit retourner la lite de plongeurs : donc p1 car il est le seul ");
        
    }*/
    @Test
    public void testGetDate() {
        assertEquals(LocalDate.of(2021, 12, 6), plonge1.getDate(), "La date doit etre  2021, 12, 6");
    }

    @Test
    public void testEstConforme() {
        //CAS CONFORME 
        p1.ajouteLicence("78", LocalDate.of(2021, 11, 24), club1);
        plonge1.ajouteParticipant(p1);
        assertTrue(plonge1.estConforme(), "La plongee doit etre conforme");

        //CAS NON CONFORME
        p1.ajouteLicence("78", LocalDate.of(2019, 11, 24), club1);
        plonge3.ajouteParticipant(p1);
        assertFalse(plonge3.estConforme(), "La plongee ne doit pas etre conforme");
    }

    //CLUB
    
    @Test 
    public void testPlongeesNonConformes(){
        //Conforme
        p1.ajouteLicence("78", LocalDate.of(2021, 11, 24), club1);
        plonge1.ajouteParticipant(p1);
        Set<Plongee> plongesConforme2 = new HashSet<Plongee>();
        assertEquals(club1.plongeesNonConformes(),plongesConforme2, "La plonge doit etre ajoute car elle est conforme" );
        
        //PlongeeNonConforme
       p1.ajouteLicence("78", LocalDate.of(2019, 11, 24), club1);
       club1.organisePlongee(plonge3);
        plonge3.ajouteParticipant(p1);
        plongesConforme2.add(plonge3);
        assertEquals(club1.plongeesNonConformes(),plongesConforme2, "La plonge doit etre ajoute car elle est conforme" );

    
    }
    
    
    
    
}

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

