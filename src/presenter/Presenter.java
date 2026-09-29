package presenter;

import models.facade.FacadeModel;
import models.facade.IFacadeModel;
import views.commons.constantes.ConstantesView;
import views.facade.FacadeViewConsole;
import views.facade.IFacadeView;

/*
 * Un cas de menu = une méthode privée. Ni System.out, ni question posée, ni "new" d'entité ici.
 * Modèles (sélection, exception, navigation) : LISEZ-MOI.md, partie 3.
 */
public class Presenter {

    private IFacadeModel facadeModel = new FacadeModel();
    private IFacadeView facadeView = new FacadeViewConsole();

    public void start() {
        initData();
        int choix;
        do {
            facadeView.afficherMenuPrincipal();
            choix = facadeView.lireChoixMenuPrincipal();
            switch (choix) {
                case 1:
                    facadeView.afficherMessage("TODO cas 1");
                    break;
                case 2:
                    facadeView.afficherMessage("TODO cas 2");
                    break;
                case 3:
                    facadeView.afficherMessage("TODO cas 3");
                    break;
                case 0:
                    facadeView.afficherMessage(ConstantesView.FIN_PROGRAMME);
                    break;
                default:
                    facadeView.afficherErreur(ConstantesView.CHOIX_INVALIDE);
            }
        } while (choix != 0);
    }

    /** Jeu de données de test : penser aux setters de TOUS les attributs (prix !). */
    private void initData() {

    }
}
