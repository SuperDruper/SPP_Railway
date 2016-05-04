package code.dao.hibernatedao;

import code.dao.daointerface.ITicketDao;
import code.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
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
    public Date getOrderDateForTicket(Ticket ticket) {
        return ticket.getOrderDate();
    }
}
