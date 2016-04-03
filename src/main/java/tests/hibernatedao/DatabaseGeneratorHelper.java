package tests.hibernatedao;

import org.apache.struts.model.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by dzmitry.antonenka on 28.03.2016.
 */
public class DatabaseGeneratorHelper {
    List<Object> listOfObjects;

    public DatabaseGeneratorHelper()
    {
        this.listOfObjects = generateTestSequenceOfObjects();
    }

    public List<Object> generateTestSequenceOfObjects() {
        // 1 Step
        Train train = generateTestTrain();
        Race race = generateTestRace();
        train.setRaces(Collections.singleton(race));
        Route route = race.getRoute();

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

    // Do not set races
    private Train generateTestTrain() {
        Train train = new Train();
        train.setId(111);
        train.setCarriageAmount(111);

        TrainType trainType = new TrainType();
        trainType.setId(111);
        trainType.setName("Test");
        trainType.setCoefficient(1.11);
        trainType.setPlacesAmount(100);
        train.setTrainType(trainType);

        return train;
    }
    // completed
    private Race generateTestRace() {
        Race race = new Race();
        race.setId(111);
        race.setRoute(generateRoute());

        return race;
    }
    // Independent method
    private Route generateRoute() {
        Route route = new Route();
        route.setId(111);
        route.setName("Test");

        return route;
    }

    private RaceStation generateRaceStation()
    {
        RaceStation raceStation = new RaceStation();
        raceStation.setId(111);
        raceStation.setArriving(new Timestamp(12312));
        raceStation.setDepature(new Timestamp(12312));
        raceStation.setStation(generateStation());

        return raceStation;
    }

    // Independent method
    private  Station generateStation() {
        Station station = new Station();
        station.setId(111);
        station.setName("Test");

        return station;
    }

    private Ticket generateTicket()
    {
        Ticket ticket = new Ticket();
        ticket.setId(111);
        ticket.setCarriageNum(1);
        ticket.setNum(111);
        ticket.setOrderDate(new Timestamp(111));

        return ticket;
    }

    // Independent method
    private Role generateRole() {
        Role role = new Role();
        role.setId(111);
        role.setName("Test");
        return role;
    }

    private User generateUser()
    {
        User user = new User();
        user.setId(111);
        user.setName("Test");
        user.setEmail("dawwd");
        user.setPassword("test");
        user.setRole(generateRole());

        return user;
    }

    public Object getDatabaseEntityForEntityClass(Class<?> entityClass) throws Exception {
        for (Object object : listOfObjects) {
            if(object.getClass().equals(entityClass)) {
                return object;
            }
        }

        throw new Exception("Cannot find database object in list !");
    }

    public Object getIdForObjectWithClass(Class<?> entityClass) throws Exception {
            for (Object object : listOfObjects) {
                if(object instanceof Role) {
                    Role role = (Role)object;
                    return new Integer(role.getId());
                } else if(object instanceof Race) {
                    Race race = (Race)object;
                    return new Integer(race.getId());
                } else if(object instanceof RaceStation) {
                    RaceStation raceStation = (RaceStation)object;
                    return new Integer(raceStation.getId());
                } else if(object instanceof Route) {
                    Route route = (Route)object;
                    return new Integer(route.getId());
                } else if(object instanceof Ticket) {
                    Ticket ticket = (Ticket)object;
                    return new Integer(ticket.getId());
                } else if(object instanceof Train) {
                    Train train = (Train)object;
                    return new Integer(train.getId());
                } else if(object instanceof User) {
                    User user = (User)object;
                    return new Integer(user.getId());
                } else {
                    throw new IOException("Uknown generic class as root set !");
                }
            }

            return null;
    }

    public Object updateForObjectWithClass(Class<?> entityClass) throws Exception {
        for (Object object : listOfObjects) {
            if(object instanceof Role) {
                Role role = (Role)object;
                role.setName("Test 2!");

            } else if(object instanceof Race) {
                Race race = (Race)object;

                Route route = new Route();
                route.setId(123);
                route.setName("some name");

                race.setRoute(route);
                return race;
            } else if(object instanceof RaceStation) {
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

        }

        throw new IOException("Array of objects is emppty !");
    }
}
