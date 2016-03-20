package org.apache.struts.dao.hibernatedao;

import org.apache.struts.dao.daointerface.IRouteDao;
import org.apache.struts.dao.daointerface.ITicketDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.Route;
import org.apache.struts.model.Ticket;
import org.apache.struts.model.User;

import java.sql.Timestamp;


/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class TicketHibernateDao extends GenericHibernateDao<Ticket, Integer> implements ITicketDao {
    public TicketHibernateDao() {
        super(Ticket.class);
    }


    public Ticket getTicketWithID(Integer ID) {
        return super.findByPK(ID);
    }

    // MARK - GETTERS

    public Integer getCarriageNumberForTicket(Ticket ticket) {
        return ticket.getCarriageNum();
    }
    public Race getRaceForTicket(Ticket ticket) {
        return ticket.getRace();
    }
    public User getUserForTicket(Ticket ticket) {
        return ticket.getUser();
    }
    public Integer getNumberForTicket(Ticket ticket) {
        return ticket.getNum();
    }
    public Timestamp getOrderDateForTicket(Ticket ticket) {
        return ticket.getOrderDate();
    }
}
