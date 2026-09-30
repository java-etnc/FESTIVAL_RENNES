package models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends Artiste{

    // Attributs
    private LocalDate dateCreationGroupe;
    private List<Soliste> membres = new ArrayList<>();

    // Constructeur
    protected Groupe(){}

    public LocalDate getDateCreationGroupe() {
        return dateCreationGroupe;
    }
    protected void setDateCreationGroupe(LocalDate dateCreationGroupe) {
        this.dateCreationGroupe = dateCreationGroupe;
    }
    public List<Soliste> getMembres() {
        return Collections.unmodifiableList(membres);
    }
    public void ajouterMembre(Soliste soliste){
        this.membres.add(soliste);
    }
    public void retirerMembre(Soliste soliste){
        this.membres.remove(soliste);
    }


    @Override
    public String getDescription() {
        String noms = "";
        for (int i = 0; i < membres.size(); i++) {
            if (i > 0) {
                noms = noms + ", ";
            }
            noms = noms + membres.get(i).getNom();
        }
        return "Groupe - " + getNom() + " (depuis " + dateCreationGroupe.getYear() + ") - "
                + membres.size() + " membres : " + noms + " - " + getCachet() + " €";
    }
}
