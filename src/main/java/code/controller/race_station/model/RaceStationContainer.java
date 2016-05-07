package code.controller.race_station.model;

import code.model.Race;
import code.model.RaceStation;
import code.model.Station;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dzmitry.antonenka on 07.05.2016.
 */
public class RaceStationContainer implements Serializable {
    private int id;
    private String depature;
    private String arriving;

    public Timestamp depatureTimestamp;
    public Timestamp arrivingTimestamp;

    private Race race;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepature() {
        return depature;
    }

    public void setDepature(String depature) {
        depatureTimestamp = Timestamp.valueOf(depature);
        this.depature = depature;
    }

    public String getArriving() {
        return arriving;
    }

    public void setArriving(String arriving) {
        arrivingTimestamp = Timestamp.valueOf(arriving);
        this.arriving = arriving;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    private Station station;

    public RaceStation getRaceStationObjectFromCurrentContainer()
    {
        RaceStation raceStation = new RaceStation();

        raceStation.setArriving(arrivingTimestamp);
        raceStation.setDepature(depatureTimestamp);

        raceStation.setRace(race);
        raceStation.setStation(station);
        raceStation.setId(id);

        return raceStation;
    }
}
