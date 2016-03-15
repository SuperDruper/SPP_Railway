package org.apache.struts.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
public class Train {
    private int id;
    private int carriageAmount;
    private Collection<Race> races;
    private TrainType trainType;

    @Id
    @Column(name = "tr_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tr_carriage_amount", nullable = false, insertable = true, updatable = true)
    public int getCarriageAmount() {
        return carriageAmount;
    }

    public void setCarriageAmount(int carriageAmount) {
        this.carriageAmount = carriageAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (carriageAmount != train.carriageAmount) return false;
        if (id != train.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + carriageAmount;
        return result;
    }

    @OneToMany(mappedBy = "train")
    public Collection<Race> getRaces() {
        return races;
    }

    public void setRaces(Collection<Race> races) {
        this.races = races;
    }

    @ManyToOne
    @JoinColumn(name = "trt_id", referencedColumnName = "trt_id", nullable = false)
    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }
}
