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
    private String dOrderDate;
    private int num;
    private int carriageNum;
    private Race race;
    private User user;

    @Column(nullable = true, insertable = true, updatable = true)
    public String getdOrderDate() {
        return dOrderDate;
    }

    @Column(name = "t_reduntant_date", nullable = false, insertable = true, updatable = true)
    public void setdOrderDate(String dOrderDate) {
        try {
            if(dOrderDate != null)
                setOrderDate(Timestamp.valueOf(dOrderDate));
        } catch (Exception exc) {
            this.orderDate = null;
        }
    }

    @Id
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
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (carriageNum != ticket.carriageNum) return false;
        if (id != ticket.id) return false;
        if (num != ticket.num) return false;
        if (orderDate != null ? !orderDate.equals(ticket.orderDate) : ticket.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + num;
        result = 31 * result + carriageNum;
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
