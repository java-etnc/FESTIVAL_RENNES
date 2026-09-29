package views.commons.constantes;

import java.util.List;

/*
 * Tout le texte affiché et les bornes de SAISIE.
 * Les plafonds métier (MAX_JOUEURS, AGE_MINIMUM) vont dans les entités.
 */
public final class ConstantesView {
    private ConstantesView() {
    }

    public static final String MENU_TITRE = "MENU PRINCIPAL";

    // Le menu de l'énoncé, dans l'ordre. "0 - sortir" est ajouté tout seul.
    public static final List<String> MENU_PRINCIPAL = List.of(
            "Créer une scène",
            "Créer un artiste",
            "Programmer un artiste sur scène",
            "Afficher la programmation d'une scène",
            "Afficher les solistes par instrument",
            "Déprogrammer un artiste"
    );

    public static final String FORMAT_DATE = "dd/MM/yyyy";

    // ---------- BORNES DE SAISIE ----------

    // ---------- MESSAGES ----------

    public static final String ERREUR_DATE_FUTURE = "La date doit etre posterieure a aujourd'hui.";
    public static final String ERREUR_DATE_PASSEE = "La date ne peut pas etre dans le futur.";
    public static final String ERREUR_AGE_MINIMUM = "Age minimum requis : ";

    public static final String FIN_PROGRAMME = "Fin du programme, au revoir !";
    public static final String CHOIX_INVALIDE = "Choix invalide.";
}
