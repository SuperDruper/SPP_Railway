package code.dao.daointerface;

import code.dao.IDao;
import code.model.Race;
import code.model.Ticket;
import code.model.User;

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
