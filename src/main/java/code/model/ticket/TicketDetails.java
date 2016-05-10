package code.model.ticket;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PC-Alyaksei on 04.05.2016.
 *
 */
public class TicketDetails implements Serializable{

    private int ticketNum;
    private int raceId;
    private String routeName;
    private String departureStationName;
    private String arriveStationName;
    private Date departureDate;
    private Date arriveDate;
    private int carriageNum;
    private int placeNum;

    public TicketDetails() {
    }

    public TicketDetails(int ticketNum, int raceId, String routeName, String departureStationName,
                         String arriveStationName, Date departureDate, Date arriveDate, int carriageNum, int placeNum) {
        this.ticketNum = ticketNum;
        this.raceId = raceId;
        this.routeName = routeName;
        this.departureStationName = departureStationName;
        this.arriveStationName = arriveStationName;
        this.departureDate = departureDate;
        this.arriveDate = arriveDate;
        this.carriageNum = carriageNum;
        this.placeNum = placeNum;
    }


    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getArriveStationName() {
        return arriveStationName;
    }

    public void setArriveStationName(String arriveStationName) {
        this.arriveStationName = arriveStationName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public int getCarriageNum() {
        return carriageNum;
    }

    public void setCarriageNum(int carriageNum) {
        this.carriageNum = carriageNum;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }
}
