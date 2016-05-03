package code.controller.race.model;

import java.util.Date;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
class RaceInfo {
    private int id;
    private String name;
    private Date departure;
    private Date arriving;
    private float cost;

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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
