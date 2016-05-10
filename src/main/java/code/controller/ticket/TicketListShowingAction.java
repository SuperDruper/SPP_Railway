package code.controller.ticket;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.controller.ticket.model.TicketContainer;
import code.infrastructure.Constants;
import code.model.Race;
import code.model.Role;
import code.model.Ticket;
import code.model.User;
import code.service.GenericService;
import code.service.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
@Authorize("admin")
public class TicketListShowingAction extends GetAction {
    private List<Ticket> tickets;
    private List<TicketContainer> ticketContainers = new ArrayList();

    private List<Race> races;

    private List<String> errorList;

    @Override
    public String view() throws Exception {
        races =  new GenericService<Race, Integer>(Race.class).findAll();

        User user = getUserFromSession();
        if(user != null) {
            setTickets(new TicketService().findAll());
        } else {
            //    throw new Exception("Cannot see tickets for user == null");
            errorList = new ArrayList<>();
            errorList.add("Cannot show tickets for undefined user. Login please.");
        }

        return SUCCESS;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }


    public void setTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticketContainers.add(new TicketContainer(ticket));
        }

        this.tickets = tickets;
    }

    public List<TicketContainer> getTicketContainers() {
        return ticketContainers;
    }
    public void setTicketContainers(List<TicketContainer> ticketContainers) {
        this.ticketContainers = ticketContainers;
    }
    public List<Race> getRaces() {
        return races;
    }
    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
