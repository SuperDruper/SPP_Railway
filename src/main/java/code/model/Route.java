package code.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Route {
    private int id;
    private String name;
    private Collection<Race> races;

    @Id
    @Column(name = "rt_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rt_name", nullable = false, insertable = true, updatable = true, length = 45)
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

        Route route = (Route) o;

        if (id != route.id) return false;
        if (name != null ? !name.equals(route.name) : route.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "route")
    public Collection<Race> getRaces() {
        return races;
    }

    public void setRaces(Collection<Race> races) {
        this.races = races;
    }
}
