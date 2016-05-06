package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRaceDao;
import code.model.Race;
import code.model.Route;
import code.model.Train;
import code.model.race.RaceSearchData;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceService extends GenericService<Race, Integer> {

    private IRaceDao dao;

    public RaceService() {
        super(Race.class);
    }

    @Override
    public IRaceDao getDao() {
        if(dao == null) {
            dao = (IRaceDao) AbstractDaoFactory.getImplDao(Race.class);
        }

        return dao;
    }

    public List<Race> findRaces(RaceSearchData searchData) {
        getDao().openCurrentSessionWithTransaction();
        List<Race> result = getDao().findRaces(searchData);
        getDao().closeCurrentSessionWithTransaction();
        return result;
    }

    public List<Race> findRacesWithRouteAndTrain(Route route, Train train) {
        getDao().openCurrentSessionWithTransaction();
        List<Race> result = getDao().getRacesWithRouteAndTrain(route, train);
        getDao().closeCurrentSessionWithTransaction();
        return result;
    }

    public Race findRaceUseInnerJOINWithTrainAndTrainTypes(int raceID)
    {
        getDao().openCurrentSessionWithTransaction();
        Race race = getDao().findRaceUseInnerJOINWithTrainAndTrainTypes(raceID);
        getDao().closeCurrentSessionWithTransaction();

        return race;
    }

    public Race findByPKWithDetails(int pk) {
        getDao().openCurrentSessionWithTransaction();
        Race result = getDao().findByPKWithDetails(pk);
        getDao().closeCurrentSessionWithTransaction();
        return result;
    }
}
