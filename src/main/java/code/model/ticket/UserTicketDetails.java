package code.model.ticket;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.service.TicketService;

import java.util.List;

/**
 * Created by PC-Alyaksei on 06.05.2016.
 */
@Authorize
public class UserTicketDetails extends GetAction {

    List<TicketDetails> ticketDetailsList;

    @Override
    public String view() throws Exception {
        ticketDetailsList = new TicketService().findTicketDetailsListByUserId(getUserIdFromSession());
        return SUCCESS;
    }

}
