package code.controller.trainType;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Train;
import code.model.TrainType;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private TrainType trainType;

    @Override
    public String create() {
        switch (action.getId()) {
            case 0:
                saveActionExecute();
                break;
            case 1: // Update
                updateActionExecute();
                break;
            case 2: //Delete
                deleteActionExecute();
                break;

            default:break;
        }

        return SUCCESS;
    }

    void saveActionExecute() { new GenericService<TrainType, Integer>(TrainType.class).persist(trainType); }
    void updateActionExecute()
    {
        new GenericService<TrainType, Integer>(TrainType.class).update(trainType);
    }
    void deleteActionExecute() {
        new GenericService<TrainType, Integer>(TrainType.class).delete(trainType);
    }

    public TrainType getTrainType() { return trainType; }
    public void setTrainType(TrainType trainType) { this.trainType = trainType; }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
