package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.ITicketDao;
import org.apache.struts.dao.daointerface.ITrainDao;
import org.apache.struts.model.Ticket;
import org.apache.struts.model.Train;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class TicketService extends GenericService<Ticket, Integer> {
    private ITicketDao dao;

    public TicketService() {
        super(Ticket.class);
    }

    @Override
    public ITicketDao getDao() {
        if(dao == null) {
            dao = (ITicketDao) AbstractDaoFactory.getImplDao(Ticket.class);
        }

        return dao;
    }
}
