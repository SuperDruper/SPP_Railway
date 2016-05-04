package code.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Entity
@Table(name = "train_type", schema = "", catalog = "railway")
public class TrainType {

    private int id;
    private String name;
    private double coefficient;
    private int placesAmount;
    private Collection<Train> trains;

    @Id
    @Column(name = "trt_id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "trt_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "trt_coefficient", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Basic
    @Column(name = "trt_places_amount", nullable = false, insertable = true, updatable = true)
    public int getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(int placesAmount) {
        this.placesAmount = placesAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainType trainType = (TrainType) o;

        if (Double.compare(trainType.coefficient, coefficient) != 0) return false;
        if (id != trainType.id) return false;
        if (placesAmount != trainType.placesAmount) return false;
        if (name != null ? !name.equals(trainType.name) : trainType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(coefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + placesAmount;
        return result;
    }

    @OneToMany(mappedBy = "trainType")
    public Collection<Train> getTrains() {
        return trains;
    }

    public void setTrains(Collection<Train> trains) {
        this.trains = trains;
    }
}
