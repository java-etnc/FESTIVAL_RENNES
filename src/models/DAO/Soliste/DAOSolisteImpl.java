package models.DAO.Soliste;

import models.DAO.MemoirDao;
import models.DAO.Scene.DAOSceneImpl;
import models.entities.Soliste;

public class DAOSolisteImpl extends MemoirDao<Soliste> implements IDAOSoliste {
    private static DAOSolisteImpl instance;

    public static DAOSolisteImpl getInstance() {
        if (instance == null) {
            instance = new DAOSolisteImpl();
        }
        return instance;
    }
}
