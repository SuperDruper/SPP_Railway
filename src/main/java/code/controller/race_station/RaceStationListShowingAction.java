package code.controller.race_station;

import code.controller.GetAction;
import code.controller.race_station.model.RaceStationContainer;
import code.controller.shared.Authorize;
import code.model.*;
import code.service.GenericService;
import code.service.RaceService;

import java.util.*;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
public class RaceStationListShowingAction extends GetAction {
    private List<RaceStationContainer> raceStationContainers = new ArrayList();

    private List<RaceStation> raceStations;
    private List<Race> races;
    private List<Station> stations;

    private HashMap<Integer, Set<Station>> stationHashMap;

    @Override
    public String view() {
        setRaces(new GenericService<Race, Integer>(Race.class).findAll());
        setRaceStations(new GenericService<RaceStation, Integer>(RaceStation.class).findAll());
        stations = new GenericService<Station, Integer>(Station.class).findAll();

        return SUCCESS;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
        List<Race> racesWithFullInfo = new ArrayList();
        for (Race race : races) {
            Race raceWithFullInfo = null;
            try {
                raceWithFullInfo = new RaceService().findRaceUseInnerJOINWithTrainAndTrainTypes(race.getId());
                if(racesWithFullInfo != null) racesWithFullInfo.add(raceWithFullInfo);
            }catch (Exception exc) {
                System.out.print("Cannot fetch race station with id = " + race.getId());
            }
        }
        races = racesWithFullInfo;


        stationHashMap = new HashMap();
        for (Race race : races) {
            Collection<RaceStation> raceStations = race.getRaceStations();
            Set<Station> stations = new HashSet();

            for (RaceStation raceStation : raceStations) {
                stations.add(raceStation.getStation());
            }
            stationHashMap.put(race.getId(), stations);
        }
    }
    public HashMap<Integer, Set<Station>> getStationHashMap() {
        return stationHashMap;
    }

    public List<Station> getStations() {
        return stations;
    }
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public List<Race> getRaces() {
        return races;
    }

    public List<RaceStationContainer> getRaceStationContainers() {
        return raceStationContainers;
    }
    public void setRaceStationContainers(List<RaceStationContainer> raceStationContainers) {
        this.raceStationContainers = raceStationContainers;
    }

    public void setRaceStations(List<RaceStation> raceStations) {
        for (RaceStation raceStation : raceStations) {
            raceStationContainers.add(new RaceStationContainer(raceStation));
        }

        this.raceStations = raceStations;
    }
}
