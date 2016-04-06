package tests.hibernatedao.Helper;

import code.model.*;
import code.service.GenericService;
import tests.hibernatedao.Helper.dbRaceManager.DBRaceManager;
import tests.hibernatedao.Helper.dbRaceManager.DBRoleManager;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by dzmitry.antonenka on 28.03.2016.
 */
public class DatabaseGeneratorHelper {
    DBRaceManager raceManager = new DBRaceManager();
    DBRoleManager roleManager = new DBRoleManager();

    List<Object> listOfObjects;

    public DatabaseGeneratorHelper()
    {
        raceManager.generatorHelper = this;
        roleManager.databaseGeneratorHelper = this;
    }

    public List<Object> generateTestSequenceOfObjects() {
        // 1 Step
        Train train = generateTestTrain();
        Race race = generateTestRace();
        race.setTrain(train);
        Route route = race.getRoute();

        train.setRaces(Collections.singleton(race));

        // 2 Step
        RaceStation raceStation = generateRaceStation();
        raceStation.setRace(race);
        Station station = raceStation.getStation();

        Ticket ticket = generateTicket();
        User user = generateUser();
        Role role = user.getRole();

        //set dependency
        user.setTickets(Collections.singleton(ticket));

        List<Object> objects = new ArrayList<Object>();
        objects.add(train);
        objects.add(race);
        objects.add(route);
        objects.add(raceStation);
        objects.add(station);
        objects.add(ticket);
        objects.add(user);
        objects.add(role);

        return objects;
    }

    public Train generateTestTrain() {
        Train train = new Train();
        train.setId(111);
        train.setCarriageAmount(111);

        TrainType trainType = new TrainType();
        trainType.setId(111);
        trainType.setName("Test");
        trainType.setCoefficient(1.11);
        trainType.setPlacesAmount(100);

        return train;
    }
    public TrainType generateTrainType() {
        TrainType trainType = new TrainType();
        trainType.setId(111);
        trainType.setName("Test");
        trainType.setCoefficient(1.11);
        trainType.setPlacesAmount(100);

        return trainType;
    }
    public Race generateTestRace() {
        Race race = new Race();
        race.setId(111);
        race.setRoute(generateTestRoute());

        return race;
    }
    public Route generateTestRoute() {
        Route route = new Route();
        route.setId(111);
        route.setName("Test");

        return route;
    }
    public Role generateTestRole() {
        Role role = new Role();
        role.setName("Test role !");
        role.setId(111);

        return role;
    }
    public RaceStation generateRaceStation(){
        RaceStation raceStation = new RaceStation();
        raceStation.setId(111);
        raceStation.setArriving(new Timestamp(12312));
        raceStation.setDepature(new Timestamp(12312));
        raceStation.setStation(generateStation());

        return raceStation;
    }
    public Station generateStation() {
        Station station = new Station();
        station.setId(111);
        station.setName("Test");

        return station;
    }
    public Ticket generateTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(111);
        ticket.setCarriageNum(1);
        ticket.setNum(111);
        ticket.setOrderDate(new Timestamp(111));

        return ticket;
    }
    public Role generateRole() {
        Role role = new Role();
        role.setId(111);
        role.setName("Test");
        return role;
    }
    public User generateUser() {
        User user = new User();
        user.setId(111);
        user.setName("Test");
        user.setEmail("dawwd");
        user.setPassword("test");
        user.setRole(generateRole());

        return user;
    }

    public Object getDatabaseEntityForEntityClass(Class<?> entityClass) throws Exception {
        if (entityClass.equals(Role.class)) {
            return roleManager.getRole();
        } else if (entityClass.equals(Race.class)) {
            return raceManager.getRace();
        }

        throw new Exception("Cannot find database object in list !");
    }

    public Object getIdForObjectWithClass(Class<?> entityClass) throws Exception {
                if(entityClass.equals(Role.class)) {
                    return roleManager.getRoleId();
                } else if(entityClass.equals(Race.class)) {
                    return raceManager.getRaceId();
                } else {
                    throw new IOException("Uknown generic class as root set !");
                }
    }

    public void updateEntityWithClass(Class<?> entityClass) {
        if (entityClass.equals(Role.class)) {
            roleManager.updateRole();
        } else if (entityClass.equals(Race.class)) {
            raceManager.updateRace();
        }
        /*
        if(object instanceof RaceStation) {
                RaceStation raceStation = (RaceStation)object;
                Station newStation = new Station();
                newStation.setId(112);
                newStation.setName("WADWA");

                raceStation.setStation(newStation);
                return raceStation;
            } else if(object instanceof Route) {
                Route route = (Route)object;
                route.setName("awddaw");

                return route;
            } else if(object instanceof Ticket) {
                Ticket ticket = (Ticket)object;
                ticket.setNum(1231);

                return ticket;
            } else if(object instanceof Train) {
                Train train = (Train)object;
                train.setCarriageAmount(12321);

                return train;
            } else if(object instanceof User) {
                User user = (User)object;
                user.setName("Test 2");
            } else {
                throw new IOException("Uknown generic class as root set !");
            }

        throw new IOException("Array of objects is emppty !");
        */
    }

    //------------------------ Interface for GenericServiceTest

    public Race getRace() {
        return raceManager.getRace();
    }

    public void deleteEntityForClass(Class<?> entityClass) {
        if(entityClass.equals(Role.class)) {
            roleManager.deleteRole();
        } else if(entityClass.equals(Race.class)) {
            raceManager.deleteCurrentRace();
        }
    }

    //----------------------- Save  data

    public void saveTrain(Train train) {
        GenericService<Train, Integer> service = new GenericService<Train, Integer>(Train.class);
        service.persist(train);
    }
    public void saveTrainType(TrainType trainType) {
        GenericService<TrainType, Integer> service = new GenericService<TrainType, Integer>(TrainType.class);
        service.persist(trainType);
    }
    public void saveRoute(Route route) {
        GenericService<Route, Integer> service = new GenericService<Route, Integer>(Route.class);
        service.persist(route);
    }

    public void saveRace(Race race) {
        GenericService<Race, Integer> service = new GenericService<Race, Integer>(Race.class);
        service.persist(race);
    }

    public void saveUser(User user) {
        GenericService<User, Integer> service = new GenericService<User, Integer>(User.class);
        service.persist(user);
    }

    public void saveTicket(Ticket ticket) {
        GenericService<Ticket, Integer> service = new GenericService<Ticket, Integer>(Ticket.class);
        service.persist(ticket);
    }

    //---------------------------------

    public void deleteRole(Role role) {
        GenericService<Role, Integer> service = new GenericService<Role, Integer>(Role.class);
        service.delete(role);
    }

    public void deleteTrain(Train train) {
        GenericService<Train, Integer> service = new GenericService<Train, Integer>(Train.class);
        service.delete(train);
    }
    public void deleteTrainType(TrainType trainType) {
        GenericService<TrainType, Integer> service = new GenericService<TrainType, Integer>(TrainType.class);
        service.delete(trainType);
    }
    public void deleteRoute(Route route) {
        GenericService<Route, Integer> service = new GenericService<Route, Integer>(Route.class);
        service.delete(route);
    }

    public void deleteRace(Race race) {
        GenericService<Race, Integer> service = new GenericService<Race, Integer>(Race.class);
        service.delete(race);
    }

    public void deleteUser(User user) {
        GenericService<User, Integer> service = new GenericService<User, Integer>(User.class);
        service.delete(user);
    }
}
