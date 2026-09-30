package presenter;

import models.entities.*;
import models.facade.FacadeModel;
import models.facade.IFacadeModel;
import models.references.Instrument;
import models.references.TypeScene;
import views.commons.constantes.ConstantesView;
import views.facade.FacadeViewConsole;
import views.facade.IFacadeView;

import java.time.LocalDate;

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
                    creerScene();
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

    private void creerScene(){
        // Saisie de l'utilisateur provenant de la view
        Scene saisirScene = facadeView.saisirScene();
        if(facadeModel.getScenes().contains(saisirScene)){
            // La méthode contains utilise.equals pour comparer du coup ça compare par nom
            facadeView.afficherErreur(ConstantesView.ERREUR_NOM_SCENE_DEJA_UTILISE);
            return;
        }
        // Enregistrer la scène dans le modèle.
        facadeModel.enregistrerScene(saisirScene);
    }

    private void initData() {
        // Les scènes
        Scene scene1 = Factory.createScene("Grande scène", TypeScene.PLEIN_AIR,8);
        Scene scene2 = Factory.createScene("Le Jardin", TypeScene.PLEIN_AIR,4);
        Scene scene3 = Factory.createScene("La Cabane", TypeScene.CHAPITEAU,3);
        Scene scene4 = Factory.createScene("Le Club", TypeScene.SALLE,2);
        // Les artistes
        Soliste soliste1 = Factory.createSoliste("Lena Morel",1200, Instrument.GUITARE);
        Soliste soliste2 = Factory.createSoliste("Tom Riva",900, Instrument.BATTERIE);
        Soliste soliste3 = Factory.createSoliste("Iris Kane",2500, Instrument.VOIX);
        Soliste soliste4 = Factory.createSoliste("Malo Brun",800, Instrument.BASSE);
        DJ dj1 = Factory.createDJ("DJ Nova",3000,90);
        DJ dj2 = Factory.createDJ("Kosmik",1500,45);
        Groupe groupe = Factory.createGroupe("The Wolves",6000, LocalDate.of(2015,03,12));
        groupe.ajouterMembre(soliste1);
        groupe.ajouterMembre(soliste2);
        // Programmation
        scene1.ajouterArtiste(groupe);
        scene3.ajouterArtiste(soliste3);
    }
}
