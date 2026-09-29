package models.references;

public enum TypeScene {
    PLEIN_AIR("Plein air"),
    CHAPITEAU("Chapiteau"),
    SALLE("Salle couverte");

    private String libelle;

    TypeScene(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
