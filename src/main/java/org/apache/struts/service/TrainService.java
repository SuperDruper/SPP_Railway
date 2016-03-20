package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.ITrainDao;
import org.apache.struts.model.Train;
import org.apache.struts.model.User;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class TrainService extends GenericService<Train, Integer> {
    private ITrainDao dao;

    public TrainService() {
        super(Train.class);
    }

    @Override
    public ITrainDao getDao() {
        if (dao == null) {
            dao = (ITrainDao) AbstractDaoFactory.getImplDao(Train.class);
        }

        return dao;
    }
}

