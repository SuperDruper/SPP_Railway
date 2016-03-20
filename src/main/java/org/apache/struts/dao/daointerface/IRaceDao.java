package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRaceDao extends IDao<Race, Integer> {
    public List<Race> getAllRacesViaStationWithName(String stationName);
    public List<Ticket> getAllTicketsForRaceWithID(Integer id);

    public List<Race> getAllRaces();
    public Race getRaceById(Integer ID);

    //MARK  -  GETTERS
    public Route getRouteForRace(Race race);
    public java.util.Collection<RaceStation> getRaceStationsForRace(Race race);
    public java.util.Collection<Ticket> getAllTicketsForRace(Race race);
    public Train getTrainForRace(Race race);
}
