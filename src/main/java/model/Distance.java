package model;

/**
 * Created by PC-Alyaksei on 29.02.2016.
 */
public class Distance {

    private Integer idFrom;
    private Integer idTo;
    private Integer distance;

    public Distance() {}

    public Distance(Integer idFrom, Integer idTo, Integer distance) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.distance = distance;
    }


    public Integer getIdFrom() {
        return idFrom;
    }

    public Integer getIdTo() {
        return idTo;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}
