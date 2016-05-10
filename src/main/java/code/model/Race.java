package code.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Race {

    private int id;
    private Route route;
    private Train train;
    private Collection<RaceStation> raceStations;
    private Collection<Ticket> tickets;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rc_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (id != race.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "rt_id", referencedColumnName = "rt_id", nullable = false)
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @ManyToOne
    @JoinColumn(name = "tr_id", referencedColumnName = "tr_id", nullable = false)
    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @OneToMany(mappedBy = "race")
    public Collection<RaceStation> getRaceStations() {
        return raceStations;
    }

    public void setRaceStations(Collection<RaceStation> raceStations) {
        this.raceStations = raceStations;
    }

    @OneToMany(mappedBy = "race")
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
