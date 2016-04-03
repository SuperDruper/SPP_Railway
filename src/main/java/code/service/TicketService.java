package code.service;

import code.dao.daointerface.ITicketDao;
import code.dao.AbstractDaoFactory;
import code.model.Ticket;

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
