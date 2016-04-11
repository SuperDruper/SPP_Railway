package code.controller.train;

import code.controller.GetAction;
import code.model.Train;
import code.model.TrainType;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
public class TrailListShowingAction extends GetAction {
    private List<Train> trains;

    @Override
    public String view() {
        trains = new GenericService<Train, Integer>(Train.class).findAll();
        return SUCCESS;
    }

    public List<Train> getTrains() { return trains; }
    public void setTrains(List<Train> trains) { this.trains = trains; }
}
