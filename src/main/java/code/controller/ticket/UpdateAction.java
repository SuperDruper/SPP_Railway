package code.controller.ticket;

import code.infrastructure.ValidationUtils;
import code.model.*;
import code.service.GenericService;
import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Role;
import code.service.GenericService;
import code.service.RaceService;
import code.service.TicketService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.util.*;

/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Ticket ticket;

    private Race race;
    private List<Race> races;
    private HashMap<Integer, List<Station>> stationHashMap;

    private List<Station> stations;
    private List<String> errorList;

    @Override
    public String create() {
        errorList = new ArrayList();

        switch (action.getId()) {
            case 0:
                saveActionExecute();
                break;
            case 1: // Update
                updateActionExecute();
                break;
            case 2: //Delete
                deleteActionExecute();
                break;

            default:break;
        }
        return SUCCESS;
    }

    void saveActionExecute() {
        User user = getUserFromSession();
        race = new RaceService().findRaceUseInnerJOINWithTrainAndTrainTypes(ticket.getRace().getId());

        if(user != null && race != null) {
            ticket.setRace(race);
            ticket.setUser(user);

            errorList = TicketService.validate(ticket, false);
            if(errorList == null || errorList.isEmpty()) {
                new GenericService<Ticket, Integer>(Ticket.class).persist(ticket);
            }
        } else {
            if(user == null)
                errorList.add("Cannot create ticket for NOT SIGNED user");

            if(race == null)
                errorList.add("Cannot create ticket for NOT SELECTED race");
        }
    }
    void updateActionExecute()
    {
        race = new RaceService().findByPKWithDetails(ticket.getRace().getId());
        ticket.setUser(getUserFromSession());
        ticket.setRace(race);

        errorList = TicketService.validate(ticket, false);
        if(errorList == null || errorList.isEmpty()) {
            new GenericService<Ticket, Integer>(Ticket.class).update(ticket);
        }
    }
    void deleteActionExecute() {
        new GenericService<Ticket, Integer>(Ticket.class).delete(ticket);
    }

    public void setRaces(List<Race> races) {
        this.races = races;
        List<Race> racesWithFullInfo = new ArrayList();
        for (Race race : races) {
            racesWithFullInfo.add(new RaceService().findRaceUseInnerJOINWithTrainAndTrainTypes(race.getId()));
        }
        races = racesWithFullInfo;

        stationHashMap = new HashMap();
        for (Race race : races) {
            Collection<RaceStation> raceStations = race.getRaceStations();
            List<Station> stations = new ArrayList();
            for (RaceStation raceStation : raceStations) {
                stations.add(raceStation.getStation());
            }
            stationHashMap.put(race.getId(), stations);
        }
    }
    public HashMap<Integer, List<Station>> getStationHashMap() {
        return stationHashMap;
    }

    // GETTERS & SETTERS
    public void setRace(Race race) {
        this.race = new RaceService().findRaceUseInnerJOINWithTrainAndTrainTypes(race.getId());

        stations = new ArrayList<Station>();
        for (RaceStation raceStation : this.race.getRaceStations()) {
            stations.add(raceStation.getStation());
        }
    }
    public List<Station> getStations() {
        return stations;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}