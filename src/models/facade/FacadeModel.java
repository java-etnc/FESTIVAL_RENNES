package models.facade;

import models.DAO.DJ.DAODJImpl;
import models.DAO.DJ.IDAODJ;
import models.DAO.Groupe.DAOGroupeImpl;
import models.DAO.Groupe.IDAOGroupe;
import models.DAO.Scene.DAOSceneImpl;
import models.DAO.Scene.IDAOScene;
import models.DAO.Soliste.DAOSolisteImpl;
import models.DAO.Soliste.IDAOSoliste;
import models.entities.Scene;
import models.utils.ModelsUtils;

import java.util.List;

/*
 * Le stockage, ce sont les DAO : un par entité, créé ICI une seule fois.
 *   private IDAOClub daoClub = new DAOClubImpl();
 * Modèle complet : LISEZ-MOI.md, partie 3.
 */
public class FacadeModel implements IFacadeModel {
    private IDAOScene sceneDAO = DAOSceneImpl.getInstance();

    private IDAOSoliste solisteDAO = DAOSolisteImpl.getInstance();
    private IDAODJ djDAO = DAODJImpl.getInstance();
    private IDAOGroupe groupeDAO = DAOGroupeImpl.getInstance();

    @Override
    public List<Scene> getScenes() {
        return ModelsUtils.CollectionToList(sceneDAO.readAll());
    }

    @Override
    public void enregistrerScene(Scene scene) {
        sceneDAO.create(scene);
    }


}
