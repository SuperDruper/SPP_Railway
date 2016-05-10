package code.controller.ticket;

import code.controller.PostAction;
import code.model.Ticket;
import code.service.GenericService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
public class DeleteUserTicketAction extends PostAction {

    private int ticketId;
    private List<String> errorList;

    @Override
    public String create() {
        errorList = new ArrayList<String>();
        GenericService<Ticket, Integer> service = new GenericService<Ticket, Integer>(Ticket.class);
        Ticket ticket = service.findByPK(ticketId);

        if (ticket == null) {
            errorList.add("Such ticket does not exists!");
        }
        else {
            if (ticket.getUser().getId() != getUserIdFromSession()) {
                errorList.add("You haven't rights to delete someone's else ticket!");
            }
            else {
                try {
                    service.delete(ticket);
                } catch (Exception e) {
                    errorList.add(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return SUCCESS;
    }


    public List<String> getErrorList() {
        return errorList;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

}