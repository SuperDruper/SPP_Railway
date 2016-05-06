package code.dao.hibernatedao;

import code.dao.daointerface.ITicketDao;
import code.model.Race;
import code.model.Ticket;
import code.model.User;
import org.hibernate.Query;

import java.sql.Timestamp;
import java.util.List;


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



    private static final String FIND_TICKETS_WITH_RACE_STATIONS_HQL =
            "SELECT t FROM Ticket t INNER JOIN FETCH t.race.raceStations WHERE t.user.id = ?";
    @Override
    public List<Ticket> findTicketsWithRaceStationsByUserId(int userId) {
        Query query = getCurrentSession().createQuery(FIND_TICKETS_WITH_RACE_STATIONS_HQL);
        query.setInteger(0, userId);
        return query.list();
    }
}
