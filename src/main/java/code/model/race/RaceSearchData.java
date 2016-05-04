package code.model.race;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class RaceSearchData {

    @Min(value = 1, message = "DepartureStationId field must be positive!")
    private int departureStationId;
    @Min(value = 1, message = "ArriveStationId field must be positive!")
    private int arriveStationId;
    @NotNull(message = "Date shouldn't be empty!")
    private Date raceDate;


    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getArriveStationId() {
        return arriveStationId;
    }

    public void setArriveStationId(int arriveStationId) {
        this.arriveStationId = arriveStationId;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }
}
