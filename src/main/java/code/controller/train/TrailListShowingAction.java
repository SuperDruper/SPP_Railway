package code.controller.train;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.Train;
import code.model.TrainType;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
@Authorize("admin")
public class TrailListShowingAction extends GetAction {
    private List<Train> trains;
    private List<TrainType> trainTypes;

    @Override
    public String view() {
        trains = new GenericService<Train, Integer>(Train.class).findAll();
        trainTypes = new GenericService<TrainType, Integer>(TrainType.class).findAll();

        return SUCCESS;
    }

    public List<TrainType> getTrainTypes() {
        return trainTypes;
    }
    public void setTrainTypes(List<TrainType> trainTypes) {
        this.trainTypes = trainTypes;
    }

    public List<Train> getTrains() { return trains; }
    public void setTrains(List<Train> trains) { this.trains = trains; }
}
