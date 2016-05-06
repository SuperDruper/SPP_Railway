package code.controller.ticket;

import code.controller.GetAction;
import code.model.ticket.TicketDetails;
import code.service.TicketService;

import java.util.List;

/**
 * Created by PC-Alyaksei on 05.05.2016.
 */
public class GetUserTicketsAction extends GetAction {

    List<TicketDetails> ticketDetailsList;

    @Override
    public String view() throws Exception {
        ticketDetailsList = new TicketService().findTicketDetailsListByUserId(getUserIdFromSession());
        return SUCCESS;
    }

}
