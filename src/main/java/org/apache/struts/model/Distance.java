package org.apache.struts.model;

import javax.persistence.*;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
@IdClass(DistancePK.class)
public class Distance {
    private int stIdFrom;
    private int stIdTo;
    private int distance;

    @Id
    @Column(name = "st_id_from", nullable = false, insertable = true, updatable = true)
    public int getStIdFrom() {
        return stIdFrom;
    }

    public void setStIdFrom(int stIdFrom) {
        this.stIdFrom = stIdFrom;
    }

    @Id
    @Column(name = "st_id_to", nullable = false, insertable = true, updatable = true)
    public int getStIdTo() {
        return stIdTo;
    }

    public void setStIdTo(int stIdTo) {
        this.stIdTo = stIdTo;
    }

    @Basic
    @Column(name = "d_distance", nullable = false, insertable = true, updatable = true)
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distance distance1 = (Distance) o;

        if (distance != distance1.distance) return false;
        if (stIdFrom != distance1.stIdFrom) return false;
        if (stIdTo != distance1.stIdTo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stIdFrom;
        result = 31 * result + stIdTo;
        result = 31 * result + distance;
        return result;
    }
}
