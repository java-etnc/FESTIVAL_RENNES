package models.facade;

import models.DAO.DJ.DAODJImpl;
import models.DAO.DJ.IDAODJ;
import models.DAO.Groupe.DAOGroupeImpl;
import models.DAO.Groupe.IDAOGroupe;
import models.DAO.Scene.DAOSceneImpl;
import models.DAO.Scene.IDAOScene;
import models.DAO.Soliste.DAOSolisteImpl;
import models.DAO.Soliste.IDAOSoliste;

/*
 * Le stockage, ce sont les DAO : un par entité, créé ICI une seule fois.
 *   private IDAOClub daoClub = new DAOClubImpl();
 * Modèle complet : LISEZ-MOI.md, partie 3.
 */
public class FacadeModel implements IFacadeModel {
    private IDAODJ dj = DAODJImpl.getInstance();

}
