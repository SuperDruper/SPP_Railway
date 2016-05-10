package code.model.race;

import java.util.Date;

/**
 * Created by PC-Alyaksei on 09.05.2016.
 */
public class RaceFullData {

    private int raceId;
    private int trainNum;
    private String routeName;
    private String departureStationName;
    private String arriveStationName;
    private Date departureDate;
    private Date arriveDate;
    private int carriages;
    private int placesLeft;


    public RaceFullData(int raceId, int trainNum, String routeName, String departureStationName,
                        String arriveStationName, Date departureDate, Date arriveDate, int carriages, int placesLeft) {
        this.raceId = raceId;
        this.trainNum = trainNum;
        this.routeName = routeName;
        this.departureStationName = departureStationName;
        this.arriveStationName = arriveStationName;
        this.departureDate = departureDate;
        this.arriveDate = arriveDate;
        this.carriages = carriages;
        this.placesLeft = placesLeft;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
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

    public int getCarriages() {
        return carriages;
    }

    public void setCarriages(int carriages) {
        this.carriages = carriages;
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(int placesLeft) {
        this.placesLeft = placesLeft;
    }
}
