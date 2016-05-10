package code.model.race;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC-Alyaksei on 02.05.2016.
 */
public class RaceDetails implements Serializable{

    private List<Carriage> carriages;


    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }
}
