package models.entities;

import models.references.Instrument;
import models.references.TypeScene;

import java.time.LocalDate;

/*
 * Le seul endroit où l'on fait "new" sur une entité (constructeurs protected,
 * d'où la Factory dans CE package). Constructeur vide puis setters :
 *
 *   public static Club createClub(String nom, LocalDate dateCreation) {
 *       Club club = new Club();
 *       club.setNom(nom);
 *       club.setDateCreation(dateCreation);
 *       return club;
 *   }
 *
 * Ni setId() (c'est le DAO), ni remplissage de collection (c'est ajouterXxx()).
 */
public final class Factory {
    private Factory() {
    }
    public static DJ createDJ(String nom, double cachet,int dureeSet){
        DJ dj = new DJ();
        dj.setNom(nom);
        dj.setCachet(cachet);
        dj.setDureeSet(dureeSet);
        return dj;
    }
    public static Soliste createSoliste(String nom, double cachet, Instrument instrument){
        Soliste soliste = new Soliste();
        soliste.setNom(nom);
        soliste.setCachet(cachet);
        soliste.setInstrument(instrument);
        return soliste;
    }
    public static Groupe createGroupe(String nom, double cachet,LocalDate dateCreation){
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        groupe.setCachet(cachet);
        groupe.setDateCreationGroupe(dateCreation);
        return groupe;
    }

    public static Scene createScene(String nom, TypeScene typeScene,int capacite){
        Scene scene = new Scene();
        scene.setNom(nom);
        scene.setTypeScene(typeScene);
        scene.setCapacite(capacite);
        return scene;
    }
}
