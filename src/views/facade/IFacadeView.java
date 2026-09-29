package views.facade;

import java.util.List;

/*
 * Ce que le Presenter voit de la vue. Ajoute une méthode par sous-menu
 * qui renvoie l'objet : Club saisirClub(); Niveau choisirNiveau();
 * Club choisirClub(List<Club> clubs);
 */
public interface IFacadeView {

    void afficherMenuPrincipal();
    int lireChoixMenuPrincipal();

    void afficherMessage(String message);
    void afficherErreur(String message);
    <E> void afficherListe(String titre, List<E> liste);

    // ---------- TES MÉTHODES PAR SOUS-MENU ----------

}
