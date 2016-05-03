package code.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class DistancePK implements Serializable {

    private int stIdFrom;
    private int stIdTo;


    public DistancePK() {}

    public DistancePK(int stIdFrom, int stIdTo) {
        this.stIdFrom = stIdFrom;
        this.stIdTo = stIdTo;
    }


    @Column(name = "st_id_from", nullable = false, insertable = true, updatable = true)
    @Id
    public int getStIdFrom() {
        return stIdFrom;
    }

    public void setStIdFrom(int stIdFrom) {
        this.stIdFrom = stIdFrom;
    }

    @Column(name = "st_id_to", nullable = false, insertable = true, updatable = true)
    @Id
    public int getStIdTo() {
        return stIdTo;
    }

    public void setStIdTo(int stIdTo) {
        this.stIdTo = stIdTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistancePK that = (DistancePK) o;

        if (stIdFrom != that.stIdFrom) return false;
        if (stIdTo != that.stIdTo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stIdFrom;
        result = 31 * result + stIdTo;
        return result;
    }
}
