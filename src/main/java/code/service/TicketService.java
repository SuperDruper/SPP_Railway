package code.service;

import code.controller.shared.Authorize;
import code.dao.daointerface.ITicketDao;
import code.dao.AbstractDaoFactory;
import code.infrastructure.ValidationUtils;
import code.model.Race;
import code.model.Ticket;
import code.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */

@Authorize
public class TicketService extends GenericService<Ticket, Integer> {
    private ITicketDao dao;

    public TicketService() {
        super(Ticket.class);
    }

    public List<Ticket> getTicketsForUser(User user)
    {
        getDao().openCurrentSessionWithTransaction();
        List<Ticket> tickets = getDao().getTicketsForUser(user);
        getDao().closeCurrentSessionWithTransaction();

        return tickets;
    }

    @Override
    public ITicketDao getDao() {
        if(dao == null) {
            dao = (ITicketDao) AbstractDaoFactory.getImplDao(Ticket.class);
        }

        return dao;
    }

    public static List<String> validate(Ticket ticket, boolean isNeedToCreate) {
        List<String> errorList = new ArrayList<>();

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
        Collection<Ticket> tickets =  race.getTickets();

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

}
