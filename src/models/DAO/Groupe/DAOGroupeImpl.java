package models.DAO.Groupe;

import models.DAO.MemoirDao;
import models.entities.Groupe;

public class DAOGroupeImpl extends MemoirDao<Groupe> implements IDAOGroupe {
    private static DAOGroupeImpl instance;

    public static DAOGroupeImpl getInstance(){
        if(instance == null){
            instance = new DAOGroupeImpl();
        }
        return instance;
    }
}
