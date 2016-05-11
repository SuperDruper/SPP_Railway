package code.dao.hibernatedao;

import code.dao.daointerface.ITicketDao;
import code.model.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


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

//    public List<Train> getTrainsWithType(TrainType trainType) {
//        Session session = getCurrentSession();
//        Criteria criteria = session.createCriteria(Train.class, "train");
//        criteria.add(Restrictions.eq("train.trainType", trainType));
//        return criteria.list();
//    }
    @Override
    public List<Ticket> getTicketsForUser(User user) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(Ticket.class, "ticket");
        criteria.add(Restrictions.eq("ticket.user", user));
        return criteria.list();
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



    private static final String FIND_TICKETS_WITH_RACE_STATIONS_BY_USER_ID_HQL =
                    "SELECT t FROM Ticket t LEFT OUTER JOIN FETCH t.race r " +
                    "LEFT OUTER JOIN FETCH r.raceStations WHERE t.user.id = ?";
    @Override
    public List<Ticket> findTicketsWithRaceStationsByUserId(int userId) {
        Query query = getCurrentSession().createQuery(FIND_TICKETS_WITH_RACE_STATIONS_BY_USER_ID_HQL);
        query.setInteger(0, userId);
        List list =  query.list();
        Set setItems = new LinkedHashSet(list);
        list.clear();
        list.addAll(setItems);
        return list;
    }

    private static final String FIND_TICKETS_BY_ALTERNATIVE_KEY =
            "SELECT t FROM Ticket t WHERE t.race.id = ? AND t.carriageNum = ? AND t.num = ?";
    @Override
    public Ticket findByAlternativeKey(int carriageNum, int placeNum, int raceId) {
        Query query = getCurrentSession().createQuery(FIND_TICKETS_BY_ALTERNATIVE_KEY);
        query.setInteger(0, raceId);
        query.setInteger(1, carriageNum);
        query.setInteger(2, placeNum);
        return (Ticket) query.uniqueResult();
    }

    private static final String FIND_TICKET_WITH_RACE_STATIONS_HQL =
            "SELECT t FROM Ticket t LEFT OUTER JOIN FETCH t.race r " +
                    "LEFT OUTER JOIN FETCH r.raceStations WHERE t.id = ?";
    @Override
    public Ticket findTicketWithRaceStationsByPK(int pk) {
        Query query = getCurrentSession().createQuery(FIND_TICKETS_WITH_RACE_STATIONS_BY_USER_ID_HQL);
        query.setInteger(0, pk);
        List<Ticket> list =  query.list();
        Set<Ticket> setItems = new LinkedHashSet<Ticket>(list);
        list.clear();
        list.addAll(setItems);
        return list.get(0);
    }

}
