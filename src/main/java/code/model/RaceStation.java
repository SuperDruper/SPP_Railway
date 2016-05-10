package code.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
@Table(name = "race_station", schema = "", catalog = "railway")
public class RaceStation {

    private int id;
    private Timestamp depature;
    private Timestamp arriving;

    private Race race;
    private Station station;

    @Id
    @Column(name = "rcst_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rcst_depature", nullable = true, insertable = true, updatable = true)
    public Timestamp getDepature() {
        return depature;
    }

    public void setDepature(Timestamp depature) {
        if(depature != null) {
            this.depature = depature;
        }
    }

    @Basic
    @Column(name = "rcst_arriving", nullable = true, insertable = true, updatable = true)
    public Timestamp getArriving() {
        return arriving;
    }

    public void setArriving(Timestamp arriving) {
        if(arriving != null) {
            this.arriving = arriving;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RaceStation that = (RaceStation) o;

        if (id != that.id) return false;
        if (arriving != null ? !arriving.equals(that.arriving) : that.arriving != null) return false;
        if (depature != null ? !depature.equals(that.depature) : that.depature != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (depature != null ? depature.hashCode() : 0);
        result = 31 * result + (arriving != null ? arriving.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "rc_id", referencedColumnName = "rc_id", nullable = false)
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @ManyToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id", nullable = false)
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
