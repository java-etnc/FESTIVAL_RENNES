package models.entities;

import models.references.TypeScene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Scene extends AbstractEntity {

    // Attributs
    private String nom;
    private TypeScene typeScene;
    private int capacite;
    private List<Artiste> programmation = new ArrayList<>();

    // Constructeur
    protected Scene() {}

    public String getNom() {
        return nom;
    }

    protected void setNom(String nom) {
        this.nom = nom;
    }

    protected TypeScene getTypeScene() {
        return typeScene;
    }

    protected void setTypeScene(TypeScene typeScene) {
        this.typeScene = typeScene;
    }

    public int getCapacite() {
        return capacite;
    }

    protected void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Artiste> getProgrammation() {
        return Collections.unmodifiableList(programmation);
    }

    public void ajouterArtiste(Artiste artiste) {
        this.programmation.add(artiste);
    }
    public void supprimerArtiste(Artiste artiste){
        this.programmation.remove(artiste);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene = (Scene) o;
        return Objects.equals(nom, scene.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}
