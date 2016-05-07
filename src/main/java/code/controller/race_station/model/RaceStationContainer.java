package code.controller.race_station.model;

import code.model.Race;
import code.model.RaceStation;
import code.model.Station;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dzmitry.antonenka on 07.05.2016.
 */
public class RaceStationContainer {
    private int id;
    private Date depature;
    private Date arriving;

    private Race race;
    private Station station;

    public RaceStation getRaceStationObjectFromCurrentContainer()
    {
        RaceStation raceStation = new RaceStation();

        raceStation.setArriving(new Timestamp(arriving.getTime()));
        raceStation.setDepature(new Timestamp(depature.getTime()));

        raceStation.setRace(race);
        raceStation.setStation(station);
        raceStation.setId(id);

        return raceStation;
    }
}
