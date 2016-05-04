package code.service;

import code.controller.shared.Authorize;
import code.dao.daointerface.ITicketDao;
import code.dao.AbstractDaoFactory;
import code.model.Ticket;
import code.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */

@Authorize
public class TicketService extends GenericService<Ticket, Integer> {
    private ITicketDao dao;

    public TicketService() {
        super(Ticket.class);
    }

    public List<Ticket> getTicketsForUser(User user)
    {
        getDao().openCurrentSessionWithTransaction();
        List<Ticket> tickets = getDao().getTicketsForUser(user);
        getDao().closeCurrentSessionWithTransaction();

        return tickets;
    }

    @Override
    public ITicketDao getDao() {
        if(dao == null) {
            dao = (ITicketDao) AbstractDaoFactory.getImplDao(Ticket.class);
        }

        return dao;
    }
}
