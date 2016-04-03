package code.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Station {
    private int id;
    private String name;
    private Collection<RaceStation> raceStations;

    @Id
    @Column(name = "st_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "st_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (id != station.id) return false;
        if (name != null ? !name.equals(station.name) : station.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "station")
    public Collection<RaceStation> getRaceStations() {
        return raceStations;
    }

    public void setRaceStations(Collection<RaceStation> raceStations) {
        this.raceStations = raceStations;
    }
}
