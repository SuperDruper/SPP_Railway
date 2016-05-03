package code.model.race;

import java.util.Date;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class RaceInfo {

    private int id;
    private String name;
    private Date departure;
    private Date arriving;
    private double cost;


    public RaceInfo() {}

    public RaceInfo(int id, String name, Date departure, Date arriving, double cost) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arriving = arriving;
        this.cost = cost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArriving() {
        return arriving;
    }

    public void setArriving(Date arriving) {
        this.arriving = arriving;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
