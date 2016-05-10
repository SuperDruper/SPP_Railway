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

    private Date depature;
    private Date arriving;

    private Station station;
    private Race race;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDepature() {
        return depature;
    }

    public void setDepature(Date depature) {
        this.depature = depature;
    }

    public Date getArriving() {
        return arriving;
    }

    public void setArriving(Date arriving) {
        this.arriving = arriving;
    }

    public RaceStationContainer() {}
    public RaceStationContainer(RaceStation raceStation)
    {
        id = raceStation.getId();
        station = raceStation.getStation();

        if(raceStation.getDepature() != null)
            depature = new Date(raceStation.getDepature().getTime());
        if(raceStation.getArriving() != null)
            arriving = new Date(raceStation.getArriving().getTime());

        race = raceStation.getRace();
    }

    public RaceStation getRaceStationObjectFromCurrentContainer()
    {
        RaceStation raceStation = new RaceStation();

        if(arriving != null)
            raceStation.setArriving(new Timestamp(arriving.getTime()));

        if(depature != null)
            raceStation.setDepature(new Timestamp(depature.getTime()));

        raceStation.setRace(race);
        raceStation.setStation(station);
        raceStation.setId(id);

        return raceStation;
    }
}
