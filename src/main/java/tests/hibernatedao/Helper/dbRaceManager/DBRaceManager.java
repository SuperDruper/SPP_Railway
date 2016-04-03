package tests.hibernatedao.Helper.dbRaceManager;

import org.apache.struts.model.Race;
import org.apache.struts.model.Route;
import org.apache.struts.model.Train;
import org.apache.struts.model.TrainType;
import org.apache.struts.service.GenericService;
import tests.hibernatedao.Helper.DatabaseGeneratorHelper;

/**
 * Created by dzmitry.antonenka on 03.04.2016.
 */
public class DBRaceManager {
    public DatabaseGeneratorHelper generatorHelper;

    //dependency entity.
    Train train;
    TrainType trainType;
    Route route;

    Race race;

    public Race getRace()
    {
        if(race == null) {
            race = createRace();
        }

        return race;
    }

    public Race createRace()
    {
        //create TrainType
        trainType = generatorHelper.generateTrainType();
        generatorHelper.saveTrainType(trainType);

        //Create Train
        train = generatorHelper.generateTestTrain();
        train.setTrainType(trainType);
        generatorHelper.saveTrain(train);

        //Create route
        route = generatorHelper.generateTestRoute();
        generatorHelper.saveRoute(route);

        race = generatorHelper.generateTestRace();
        race.setTrain(train);
        race.setRoute(route);

        return race;
    }

    public void updateRace() {
        route = new Route();
        route.setId(123);
        route.setName("some name");

        generatorHelper.saveRoute(route);
        race.setRoute(route);
    }

    public void deleteCurrentRace() {
        generatorHelper.deleteRace(race);
        generatorHelper.deleteRoute(route);
        generatorHelper.deleteTrain(train);
        generatorHelper.deleteTrainType(trainType);

        race = null;
    }

    // Getters

    public Race getCurrentRace() {
        return race;
    }

    public Object getRaceId()
    {
       return new Integer(race.getId());
    }
}
