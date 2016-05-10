package code.dao.daointerface;

import code.dao.IDao;
import code.model.Race;
import code.model.Ticket;
import code.model.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface ITicketDao extends IDao<Ticket, Integer> {
    public Ticket getTicketWithID(Integer ID);
    public List<Ticket> getTicketsForUser(User user);

    //MARK - GETTER
    public Integer getCarriageNumberForTicket(Ticket ticket);
    public Race getRaceForTicket(Ticket ticket);
    public User getUserForTicket(Ticket ticket);
    public Integer getNumberForTicket(Ticket ticket);
    public Timestamp getOrderDateForTicket(Ticket ticket);

    List<Ticket> findTicketsWithRaceStationsByUserId(int userId);

    Ticket findByAlternativeKey(int carriageNum, int placeNum, int raceId);
}
