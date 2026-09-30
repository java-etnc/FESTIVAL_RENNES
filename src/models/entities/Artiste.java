package models.entities;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Artiste extends AbstractEntity {
    private String nom;
    private double cachet;
    private LocalDate dateProgrammation;

    protected Artiste(){}
    public abstract String getDescription();

    public String getNom() {
        return nom;
    }

    protected void setNom(String nom) {
        this.nom = nom;
    }

    public double getCachet() {
        return cachet;
    }

    protected void setCachet(double cachet) {
        this.cachet = cachet;
    }

    public LocalDate getDateProgrammation() {
        return dateProgrammation;
    }

    public void setDateProgrammation(LocalDate dateProgrammation) {
        this.dateProgrammation = dateProgrammation;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Artiste)) return false;
        Artiste artiste = (Artiste) o;
        return Objects.equals(nom, artiste.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
