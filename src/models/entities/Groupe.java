package models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Groupe extends Artiste{

    private LocalDate dateCreationGroupe;
    private List<Soliste> membres = new ArrayList<>();

    public LocalDate getDateCreationGroupe() {
        return dateCreationGroupe;
    }
    protected void setDateCreationGroupe(LocalDate dateCreationGroupe) {
        this.dateCreationGroupe = dateCreationGroupe;
    }
    public List<Soliste> getMembres() {
        return Collections.unmodifiableList(membres);
    }
    protected void setMembres(List<Soliste> membres) {
        this.membres = membres;
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
