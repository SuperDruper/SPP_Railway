package code.controller.trainType;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.TrainType;
import code.model.User;
import code.service.GenericService;
import code.service.UserService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
@Authorize("admin")
public class TrainTypeListShowingAction extends GetAction {
    private List<TrainType> trainTypes = new GenericService<TrainType, Integer>(TrainType.class).findAll();

    @Override
    public String view() {
        trainTypes = new GenericService<TrainType, Integer>(TrainType.class).findAll();
        return SUCCESS;
    }

    public List<TrainType> getTrainTypes() { return trainTypes; }
    public void setTrainTypes(List<TrainType> trainTypes) { this.trainTypes = trainTypes; }
}
