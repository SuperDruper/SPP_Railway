package code.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Ticket {

    private int id;
    private Timestamp orderDate;
    private int num;
    private int carriageNum;
    private Race race;
    private User user;
    private Station stationFrom;
    private Station stationTo;

    @ManyToOne
    @JoinColumn(name = "st_id_from", referencedColumnName = "st_id", nullable = false)
    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    @ManyToOne
    @JoinColumn(name = "st_id_to", referencedColumnName = "st_id", nullable = false)
    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "t_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "t_order_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        if(orderDate != null) {
            this.orderDate = orderDate;
        }
    }

    @Basic
    @Column(name = "t_num", nullable = false, insertable = true, updatable = true)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "t_carriage_num", nullable = false, insertable = true, updatable = true)
    public int getCarriageNum() {
        return carriageNum;
    }

    public void setCarriageNum(int carriageNum) {
        this.carriageNum = carriageNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (carriageNum != ticket.carriageNum) return false;
        if (num != ticket.num) return false;
        if (!race.equals(ticket.race)) return false;
        if (!stationFrom.equals(ticket.stationFrom)) return false;
        if (!stationTo.equals(ticket.stationTo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + carriageNum;
        result = 31 * result + race.hashCode();
        result = 31 * result + stationFrom.hashCode();
        result = 31 * result + stationTo.hashCode();
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
    @JoinColumn(name = "u_id", referencedColumnName = "u_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
