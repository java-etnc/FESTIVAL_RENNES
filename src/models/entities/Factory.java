package models.entities;

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


}
