package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRaceStationsDao;
import code.model.RaceStation;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceStationService extends GenericService<RaceStation, Integer> {

    private IRaceStationsDao dao;

    public RaceStationService() {
        super(RaceStation.class);
    }

    @Override
    public IRaceStationsDao getDao() {
        if(dao == null) {
            dao = (IRaceStationsDao) AbstractDaoFactory.getImplDao(RaceStation.class);
        }

        return dao;
    }

    public List<RaceStation> getRaceStationsByRaceId(int raceId) {
        getDao().openCurrentSession();
        List<RaceStation> raceStations = getDao().findByRaceId(raceId);
        getDao().closeCurrentSession();
        return raceStations;
    }

    public RaceStation getDepartureRaceStationByRacePK(int racePK) {
        getDao().openCurrentSession();
        RaceStation result = getDao().getDepartureRaceStationByRacePK(racePK);
        getDao().closeCurrentSession();
        return result;
    }

    public RaceStation getArriveRaceStationByRacePK(int racePK) {
        getDao().openCurrentSession();
        RaceStation result = getDao().getArriveRaceStationByRacePK(racePK);
        getDao().closeCurrentSession();
        return result;
    }


}
