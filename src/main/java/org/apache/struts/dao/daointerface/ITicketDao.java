package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.Ticket;
import org.apache.struts.model.User;

import java.sql.Timestamp;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface ITicketDao extends IDao<Ticket, Integer> {
    public Ticket getTicketWithID(Integer ID);

    // MARK - GETTERS
    public Integer getCarriageNumberForTicket(Ticket ticket);
    public Race getRaceForTicket(Ticket ticket);
    public User getUserForTicket(Ticket ticket);
    public Integer getNumberForTicket(Ticket ticket);
    public Timestamp getOrderDateForTicket(Ticket ticket);
}
