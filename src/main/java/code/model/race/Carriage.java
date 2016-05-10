package code.model.race;

import java.util.List;

/**
 * Created by PC-Alyaksei on 02.05.2016.
 */
public class Carriage {

    private int num;
    private List<Place> places;


    public Carriage(int num, List<Place> places) {
        this.num = num;
        this.places = places;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
