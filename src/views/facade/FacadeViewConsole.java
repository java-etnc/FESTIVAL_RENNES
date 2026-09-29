package views.facade;

import views.commons.constantes.ConstantesView;
import views.commons.utils.AffichageConsole;
import views.commons.utils.LectureConsole;
import views.commons.utils.ViewUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/*
 * La vue pose les questions, contrôle les SAISIES et renvoie l'objet au Presenter.
 * Une méthode publique par sous-menu, par exemple :
 *
 *   public Club saisirClub() {
 *       String nom = lireTexte("Nom du club : ");
 *       LocalDate date = lireDatePassee("Date de création : ");
 *       return Factory.createClub(nom, date);
 *   }
 *   public Niveau choisirNiveau() { return ViewUtils.choixEnumMenu("Niveau :", Niveau.class); }
 *   public Club choisirClub(List<Club> clubs) { return ViewUtils.choixDansListe("Club :", clubs); }
 *   public List<Joueur> choisirJoueurs(List<Joueur> dispo) { return ViewUtils.choixMultipleDansListe("Joueurs :", dispo); }
 */
public class FacadeViewConsole implements IFacadeView {

    @Override
    public void afficherMenuPrincipal() {
        AffichageConsole.afficherMenuEntoureAvecOptionSortie(ConstantesView.MENU_PRINCIPAL, ConstantesView.MENU_TITRE);
    }

    @Override
    public int lireChoixMenuPrincipal() {
        return LectureConsole.lectureChoixInt(0, ConstantesView.MENU_PRINCIPAL.size());
    }

    @Override
    public void afficherMessage(String message) {
        AffichageConsole.afficherMessageAvecSautLigne(message);
    }

    @Override
    public void afficherErreur(String message) {
        AffichageConsole.afficherErreur(message);
    }

    @Override
    public <E> void afficherListe(String titre, List<E> liste) {
        ViewUtils.afficherListe(titre, liste);
    }

    // ---------- TES MÉTHODES PAR SOUS-MENU ----------


    // ---------- BOÎTE À OUTILS PRIVÉE (grisée tant qu'elle ne sert pas) ----------

    private String lireTexte(String question) {
        AffichageConsole.afficherMessageAvecSautLigne(question);
        return LectureConsole.lectureChaineCaracteres();
    }

    private int lireEntier(String question, int min, int max) {
        AffichageConsole.afficherMessageAvecSautLigne(question + " (entre " + min + " et " + max + ")");
        return LectureConsole.lectureChoixInt(min, max);
    }

    private double lireDouble(String question) {
        AffichageConsole.afficherMessageAvecSautLigne(question);
        return LectureConsole.lectureDouble();
    }

    private LocalDate lireDate(String question) {
        AffichageConsole.afficherMessageAvecSautLigne(question + " (" + ConstantesView.FORMAT_DATE + ")");
        return LectureConsole.lectureLocalDate(ConstantesView.FORMAT_DATE);
    }

    /** Strictement après aujourd'hui. */
    private LocalDate lireDateFuture(String question) {
        LocalDate date = lireDate(question);
        while (!date.isAfter(LocalDate.now())) {
            afficherErreur(ConstantesView.ERREUR_DATE_FUTURE);
            date = lireDate(question);
        }
        return date;
    }

    /** Aujourd'hui ou avant. */
    private LocalDate lireDatePassee(String question) {
        LocalDate date = lireDate(question);
        while (date.isAfter(LocalDate.now())) {
            afficherErreur(ConstantesView.ERREUR_DATE_PASSEE);
            date = lireDate(question);
        }
        return date;
    }

    /** Âge minimum : lireDateNaissance("Né le : ", Joueur.AGE_MINIMUM) */
    private LocalDate lireDateNaissance(String question, int ageMinimum) {
        LocalDate date = lireDatePassee(question);
        while (Period.between(date, LocalDate.now()).getYears() < ageMinimum) {
            afficherErreur(ConstantesView.ERREUR_AGE_MINIMUM + ageMinimum + " ans.");
            date = lireDatePassee(question);
        }
        return date;
    }
}
