package models.DAO.Scene;

import models.DAO.Groupe.DAOGroupeImpl;
import models.DAO.MemoirDao;
import models.entities.Scene;

public class DAOSceneImpl extends MemoirDao<Scene> implements IDAOScene {
    private static DAOSceneImpl instance;

    public static DAOSceneImpl getInstance(){
        if(instance == null){
            instance = new DAOSceneImpl();
        }
        return instance;
    }
}
