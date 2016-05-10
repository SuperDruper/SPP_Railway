package code.dao.daointerface;

import code.model.*;
import code.dao.IDao;
import code.model.race.RaceSearchData;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRaceDao extends IDao<Race, Integer> {
    public List<Race> getAllRacesViaStationWithName(String stationName);
    public List<Ticket> getAllTicketsForRaceWithID(Integer id);

    public List<Race> getAllRaces();
    public Race getRaceById(Integer ID);

    public List<Race> getRacesWithRouteAndTrain(Route route, Train train);

    //MARK  -  GETTERS
    public Route getRouteForRace(Race race);
    public java.util.Collection<RaceStation> getRaceStationsForRace(Race race);
    public java.util.Collection<Ticket> getAllTicketsForRace(Race race);
    public Train getTrainForRace(Race race);


    /**
     * Used for retrieving race with raceStations using SQL query.
     * @param searchData - parameter for query
     * @return List of Races
     */
    List<Race> findRaces(RaceSearchData searchData);

    /**
     * Used for retrieving race with Race with train and trainType using SQL query.
     * HibernateDAO implements this interface.
     * @param
     * @return unique Race
     */
    Race findRaceUseInnerJOINWithTrainAndTrainTypes(int raceID);

    Race findByPKWithDetails(int pk);

    List<Race> findRacesWithDetailsByRouteId(int routeId);
}
