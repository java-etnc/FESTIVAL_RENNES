package views.commons.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * Choix génériques pour la vue. Les libellés affichés viennent de toString() :
 * enum -> libellé, entité -> toString() qui renvoie getDescription().
 */
public final class ViewUtils {

    private ViewUtils() {
    }

    /** Menu numéroté des valeurs d'un enum : ViewUtils.choixEnumMenu("Type :", TypeVin.class) */
    public static <E extends Enum<E>> E choixEnumMenu(String titre, Class<E> eEnum) {
        E[] valeurs = eEnum.getEnumConstants();
        List<String> menuString = new ArrayList<>();
        for (E e : valeurs) {
            menuString.add(e.toString());
        }
        AffichageConsole.afficherMessageAvecSautLigne(titre);
        AffichageConsole.afficherMenuSimple(menuString);
        int choix = LectureConsole.lectureChoixInt(1, valeurs.length);
        return valeurs[choix - 1];
    }

    /** Choisir un objet dans une liste. Renvoie null si la liste est vide. */
    public static <E> E choixDansListe(String titre, List<E> liste) {
        if (liste.isEmpty()) {
            return null;
        }
        List<String> menuString = new ArrayList<>();
        for (E e : liste) {
            menuString.add(e.toString());
        }
        AffichageConsole.afficherMessageAvecSautLigne(titre);
        AffichageConsole.afficherMenuSimple(menuString);
        int choix = LectureConsole.lectureChoixInt(1, liste.size());
        return liste.get(choix - 1);
    }

    /** Sélection multiple : l'élément choisi sort des disponibles, 0 pour terminer. */
    public static <E> List<E> choixMultipleDansListe(String titre, List<E> disponibles) {
        List<E> restants = new ArrayList<>(disponibles);
        List<E> choisis = new ArrayList<>();
        int choix = -1;
        while (choix != 0 && !restants.isEmpty()) {
            List<String> menuString = new ArrayList<>();
            for (E e : restants) {
                menuString.add(e.toString());
            }
            AffichageConsole.afficherMessageAvecSautLigne(titre);
            AffichageConsole.afficherMenuSimpleAvecOptionSortie(menuString, "terminer");
            choix = LectureConsole.lectureChoixInt(0, restants.size());
            if (choix != 0) {
                choisis.add(restants.remove(choix - 1));
            }
        }
        return choisis;
    }

    /** Question oui / non sous forme de menu 1 - oui, 2 - non. */
    public static boolean confirmer(String question) {
        List<String> menuString = new ArrayList<>();
        menuString.add("Oui");
        menuString.add("Non");
        AffichageConsole.afficherMessageAvecSautLigne(question);
        AffichageConsole.afficherMenuSimple(menuString);
        return LectureConsole.lectureChoixInt(1, 2) == 1;
    }

    /** Titre puis une ligne par élément (toString), ou "(aucun)". */
    public static <E> void afficherListe(String titre, List<E> liste) {
        AffichageConsole.afficherMessageAvecSautLigne(titre);
        if (liste.isEmpty()) {
            AffichageConsole.afficherMessageAvecSautLigne("\t(aucun)");
        }
        for (E e : liste) {
            AffichageConsole.afficherMessageAvecSautLigne("\t- " + e);
        }
    }
}
