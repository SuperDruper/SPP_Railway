package code.model.ticket;

/**
 * Created by PC-Alyaksei on 04.05.2016.
 */
public class TicketDataToOrder {

    private int raceId;
    private String departureStationName;
    private String arriveStationName;
    private int carriageNum;
    private int placeNum;


    public TicketDataToOrder() {}


    public TicketDataToOrder(int raceId, String departureStationName, String arriveStationName, int carriageNum, int placeNum) {
        this.raceId = raceId;
        this.departureStationName = departureStationName;
        this.arriveStationName = arriveStationName;
        this.carriageNum = carriageNum;
        this.placeNum = placeNum;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
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
