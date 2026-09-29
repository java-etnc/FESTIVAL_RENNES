package models.entities;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public abstract class Artiste extends AbstractEntity {
    private String nom;
    private double cachet;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    protected void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Artiste artiste = (Artiste) o;
        return Objects.equals(nom, artiste.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }
}
