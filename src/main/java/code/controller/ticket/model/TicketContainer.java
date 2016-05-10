package code.controller.ticket.model;

import code.model.Race;
import code.model.Station;
import code.model.Ticket;
import code.model.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dzmitry.antonenka on 09.05.2016.
 */
public class TicketContainer implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCarriageNum() {
        return carriageNum;
    }

    public void setCarriageNum(int carriageNum) {
        this.carriageNum = carriageNum;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    private Date orderDate;
    private int num;
    private int carriageNum;
    private Race race;
    private User user;
    private Station stationFrom;
    private Station stationTo;

    public TicketContainer(Ticket ticket) {
        id = ticket.getId();
        orderDate = new Date(ticket.getOrderDate().getTime());
        num = ticket.getNum();
        carriageNum = ticket.getCarriageNum();
        race = ticket.getRace();
        user = ticket.getUser();
        stationFrom = ticket.getStationFrom();
        stationTo = ticket.getStationTo();
    }
    public TicketContainer() {}

    public static Ticket getTicketFromTicketContainer(TicketContainer ticketContainer) {
         Ticket ticket = new Ticket();
         ticket.setId(ticketContainer.getId());

        if(ticketContainer.getOrderDate() != null)
            ticket.setOrderDate(new Timestamp(ticketContainer.getOrderDate().getTime()));

        ticket.setCarriageNum(ticketContainer.getCarriageNum());
        ticket.setRace(ticketContainer.getRace());
        ticket.setUser(ticketContainer.getUser());
        ticket.setNum(ticketContainer.getNum());
        ticket.setStationFrom(ticketContainer.getStationFrom());
        ticket.setStationTo(ticketContainer.getStationTo());

        return ticket;
    }
}
