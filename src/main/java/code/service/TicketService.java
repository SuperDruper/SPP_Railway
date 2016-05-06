package code.service;

import code.dao.daointerface.ITicketDao;
import code.dao.AbstractDaoFactory;
import code.model.*;
import code.model.ticket.TicketDataToOrder;
import code.model.ticket.TicketDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public List<TicketDetails> findTicketDetailsListByUserId(int userId) {
        getDao().openCurrentSession();

        List<Ticket> tickets = getDao().findTicketsWithRaceStationsByUserId(userId);
        List<TicketDetails> ticketDetailsList = new ArrayList<>();

        for (Ticket ticket : tickets) {
            TicketDetails ticketDetails = convertToTicketDetails(ticket);
            ticketDetailsList.add(ticketDetails);
        }
        getDao().closeCurrentSession();

        return ticketDetailsList;
    }
    private TicketDetails convertToTicketDetails(Ticket ticket) {
        RaceStation departureRaceStation = getRaceStationFromListById(
                ticket.getRace().getRaceStations(), ticket.getStationFrom().getId());
        RaceStation arriveRaceStation = getRaceStationFromListById(
                ticket.getRace().getRaceStations(), ticket.getStationTo().getId());

        Date departure = departureRaceStation.getDepature();
        Date arriving =  arriveRaceStation.getArriving();

        return new TicketDetails(
                ticket.getNum(),
                ticket.getRace().getId(),
                ticket.getRace().getRoute().getName(),
                ticket.getStationFrom().getName(),
                ticket.getStationTo().getName(),
                departure,
                arriving,
                ticket.getCarriageNum(),
                ticket.getNum()
        );
    }
    private RaceStation getRaceStationFromListById(Collection<RaceStation> list, int stationId) {
        RaceStation result = null;

        for (RaceStation raceStation : list) {
            if (raceStation.getStation().getId() == stationId) {
                result = raceStation;
                break;
            }
        }

        return result;
    }


    public int orderTicketAndGetTicketNum(TicketDataToOrder ticketDataToOrder, User user) {
        GenericService<Station, Integer> stationService = new GenericService<>(Station.class);
        RaceService raceService = new RaceService();

        Station stationFrom = stationService.getModelByUniqueStringField("name",
                ticketDataToOrder.getDepartureStationName());
        Station stationTo = stationService.getModelByUniqueStringField("name",
                ticketDataToOrder.getArriveStationName());
        Timestamp data = new Timestamp(new Date().getTime());
        Race race = raceService.findByPK(ticketDataToOrder.getRaceId());


        Ticket ticket = new Ticket();
        ticket.setNum(ticketDataToOrder.getPlaceNum());
        ticket.setCarriageNum(ticketDataToOrder.getCarriageNum());
        ticket.setOrderDate(data);
        ticket.setRace(race);
        ticket.setStationFrom(stationFrom);
        ticket.setStationTo(stationTo);
        ticket.setUser(user);


        getDao().openCurrentSessionWithTransaction();
        getDao().persist(ticket);
        getDao().closeCurrentSessionWithTransaction();

        return ticket.getId();
    }
}
