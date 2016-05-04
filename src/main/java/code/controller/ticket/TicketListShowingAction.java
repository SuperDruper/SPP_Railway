package code.controller.ticket;

import code.controller.GetAction;
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
public class TicketListShowingAction extends GetAction {
    private List<Ticket> tickets;
    private List<Race> races;

    private List<String> errorList;

    @Override
    public String view() throws Exception {
        races =  new GenericService<Race, Integer>(Race.class).findAll();

        User user = getUserFromSession();
        if(user != null) {
            tickets = new TicketService().getTicketsForUser(user);
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
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public List<Race> getRaces() {
        return races;
    }
    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
