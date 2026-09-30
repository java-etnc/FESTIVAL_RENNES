package models.DAO.DJ;

import models.DAO.MemoirDao;
import models.entities.DJ;

public class DAODJImpl extends MemoirDao<DJ> implements IDAODJ {
    private static DAODJImpl instance;
    private DAODJImpl(){}

    public static DAODJImpl getInstance(){
        if(instance == null){
            instance = new DAODJImpl();
        }
        return  instance;
    }

}
