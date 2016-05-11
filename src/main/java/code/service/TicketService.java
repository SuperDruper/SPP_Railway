package code.service;

import code.controller.shared.Authorize;
import code.dao.daointerface.ITicketDao;
import code.dao.AbstractDaoFactory;
import code.infrastructure.ValidationUtils;
import code.model.*;
import code.model.ticket.TicketDataToOrder;
import code.model.ticket.TicketDetails;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.sql.Timestamp;

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

    public static List<String> validate(Ticket ticket, boolean isNeedToCreate) {
        List<String> errorList = new ArrayList();

        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Ticket>> set = validator.validate(ticket);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidationTicketsForRace(errorList, ticket, ticket.getRace(), isNeedToCreate);
        }
        else {
            return errorList;
        }
    }

    // HELPERS
    public static List<String> furtherValidationTicketsForRace(List<String> errorList, Ticket ticketToCreate, Race race, boolean isNeedToCreate) {
        boolean approved = true;
        Collection<Ticket> tickets = race.getTickets();

        if(isNeedToCreate && tickets != null && !tickets.isEmpty()) {
            for (Ticket ticket : tickets)
            {
                if(ticket.getCarriageNum() == ticketToCreate.getCarriageNum() && ticket.getNum() == ticketToCreate.getNum())
                {
                    errorList.add("Cannot create ticket, 'cause place has already booked");
                    approved = false;
                }
            }
        }

        if( new Date(ticketToCreate.getOrderDate().getTime()).before(new Date()))
        {
            errorList.add("Cannot create ticket with time in the past !");
            approved = false;
        }

        if( !(ticketToCreate.getCarriageNum() > 0 && ticketToCreate.getCarriageNum() <= race.getTrain().getCarriageAmount()) ) {
            errorList.add("Entered invalid carriage number value. " + "Max carriage count is " + ticketToCreate.getRace().getTrain().getCarriageAmount());
            approved = false;
        }

        if( !(ticketToCreate.getNum() > 0 && ticketToCreate.getNum() <= race.getTrain().getTrainType().getPlacesAmount()) ) {
            errorList.add("Entered invalid place of carriage value." + "Max count of places is " + race.getTrain().getTrainType().getPlacesAmount());
            approved = false;
        }

        return errorList;
    }


    public List<TicketDetails> findTicketDetailsListByUserId(int userId) {
        getDao().openCurrentSession();

        List<Ticket> tickets = getDao().findTicketsWithRaceStationsByUserId(userId);
        List<TicketDetails> ticketDetailsList = new ArrayList();

        getDao().closeCurrentSession();

        for (Ticket ticket : tickets) {
            TicketDetails ticketDetails = convertToTicketDetails(ticket);
            ticketDetailsList.add(ticketDetails);
        }

        return ticketDetailsList;
    }

    public TicketDetails findTicketDetailsByPK(int pk) {
        getDao().openCurrentSession();
        Ticket ticket = getDao().findTicketWithRaceStationsByPK(pk);
        getDao().closeCurrentSession();

        return convertToTicketDetails(ticket);
    }


    private TicketDetails convertToTicketDetails(Ticket ticket) {
        RaceStation departureRaceStation = getRaceStationFromListById(
                ticket.getRace().getRaceStations(), ticket.getStationFrom().getId());
        RaceStation arriveRaceStation = getRaceStationFromListById(
                ticket.getRace().getRaceStations(), ticket.getStationTo().getId());

        Date departure = null;
        Date arriving = null;
        if(departureRaceStation != null)
            departure = departureRaceStation.getDepature();
        if(arriveRaceStation != null)
             arriving =  arriveRaceStation.getArriving();

        return new TicketDetails(
                ticket.getId(),
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
        GenericService<Station, Integer> stationService = new GenericService(Station.class);
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

    public boolean ticketIsAlreadyExists(int raceId, int carriageNum, int placeNum) {
        getDao().openCurrentSessionWithTransaction();
        Ticket ticket = getDao().findByAlternativeKey(carriageNum, placeNum, raceId);
        getDao().closeCurrentSessionWithTransaction();
        return ticket != null;
    }

    public boolean ticketDataIsPossible(int raceId, int carriageNum, int placeNum) {
        Race race = new RaceService().findByPK(raceId);

        if (race == null) return false;

        Train train = race.getTrain();
        int carriageMaxNum = train.getCarriageAmount();
        int placeMaxNum = train.getTrainType().getPlacesAmount();

        return carriageNum > 0 && carriageNum <= carriageMaxNum
               && placeNum > 0 && placeNum <= placeMaxNum;
    }

}
