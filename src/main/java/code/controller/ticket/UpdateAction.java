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
    private List<String> errorList;

    @Override
    public String create() {
        errorList = new ArrayList<>();

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

            if(validate(ticket, true)) {
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
        if(validate(ticket, false)) {
            ticket.setUser(getUserFromSession());
            ticket.setRace(new GenericService<Race, Integer>(Race.class).findByPK(ticket.getRace().getId()));
            new GenericService<Ticket, Integer>(Ticket.class).update(ticket);
        }
    }
    void deleteActionExecute() {
        new GenericService<Ticket, Integer>(Ticket.class).delete(ticket);
    }



    private boolean validate(Ticket ticket, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Ticket>> set = validator.validate(ticket);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            if(isNeedToCreate) {
                return furtherValidationTicketsForRace(race, ticket);
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    // HELPERS
    boolean furtherValidationTicketsForRace(Race race, Ticket ticketToCreate)
    {
        boolean approved = true;
        Collection<Ticket> tickets =  race.getTickets();

        if(tickets != null && !tickets.isEmpty()) {
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
            errorList.add("Entered invalid carriage number value. " + "Max carriage count is " + ticket.getRace().getTrain().getCarriageAmount());
            approved = false;
        }

        if( !(ticketToCreate.getNum() > 0 && ticketToCreate.getNum() <= race.getTrain().getTrainType().getPlacesAmount()) ) {
            errorList.add("Entered invalid place of carriage value." + "Max count of places is " + race.getTrain().getTrainType().getPlacesAmount());
            approved = false;
        }

        return approved;
    }


    // GETTERS & SETTERS
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