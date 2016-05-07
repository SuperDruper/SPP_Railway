package code.controller.ticket;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.ticket.TicketDataToOrder;
import code.service.TicketService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PC-Alyaksei on 04.05.2016.
 */
@Authorize
public class TicketOrderAction extends PostAction {

    private TicketDataToOrder ticketDataToOrder;

    private int ticketNum;
    private List<String> errorList = new ArrayList<>();


    @Override
    public String create() throws Exception {
        try {
            TicketService service = new TicketService();
            int race = ticketDataToOrder.getRaceId();
            int carriage = ticketDataToOrder.getCarriageNum();
            int place = ticketDataToOrder.getPlaceNum();

            if (!service.ticketDataIsPossible(race, carriage, place)) {
                errorList.add("Race train can't have such pair of carriage and place numbers!");
                return SUCCESS;
            }

            if (service.ticketIsAlreadyExists(race, carriage, place)) {
                errorList.add("This ticket have been ordered while you were choosing " +
                        "carriage and place. Try to choose another one! =)");
                return SUCCESS;
            }

            ticketNum = service.orderTicketAndGetTicketNum(ticketDataToOrder, getUserFromSession());
        } catch (Exception ex) {
            errorList.add("Your request data is incorrect ... or the server is wrong!");
            ex.printStackTrace();
        }

        return SUCCESS;
    }


    public int getTicketNum() {
        return ticketNum;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setTicketDataToOrder(TicketDataToOrder ticketDataToOrder) {
        this.ticketDataToOrder = ticketDataToOrder;
    }

}
