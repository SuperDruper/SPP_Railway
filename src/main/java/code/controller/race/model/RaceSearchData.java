package code.controller.race.model;

import java.util.Date;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
class RaceSearchData {

    private int stationIdFrom;
    private int stationIdTo;
    private Date date;


    public int getStationIdFrom() {
        return stationIdFrom;
    }

    public void setStationIdFrom(int stationIdFrom) {
        this.stationIdFrom = stationIdFrom;
    }

    public int getStationIdTo() {
        return stationIdTo;
    }

    public void setStationIdTo(int stationIdTo) {
        this.stationIdTo = stationIdTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
