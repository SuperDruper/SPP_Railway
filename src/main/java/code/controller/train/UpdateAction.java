package code.controller.train;

import code.controller.PostAction;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Ticket;
import code.model.Train;
import code.model.TrainType;
import code.service.GenericService;
import code.service.TrainService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
public class UpdateAction extends PostAction {
    private Train train;
    private CrudAction action;
    private List<String> errorList;

    @Override
    public String create() {

        switch (action.getId()) {
            case 0: //Create
                createActionExecute();
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

    private void updateActionExecute()
    {
        TrainType trainType = new GenericService<TrainType, Integer>(TrainType.class).findByPK(train.getTrainType().getId());
        train.setTrainType(trainType);

        if(!validationTrainObject(train, false)) return;

        new TrainService().update(train);
    }

    private void deleteActionExecute()
    {
        new TrainService().delete(train);
    }

    private void createActionExecute()
    {
        TrainType trainType = new GenericService<TrainType, Integer>(TrainType.class).findByPK(train.getTrainType().getId());
        train.setTrainType(trainType);

        if(!validationTrainObject(train, true)) return;
        new TrainService().persist(train);
    }

    public String view() {
        return SUCCESS;
    }


    private Boolean objectHasStoredInDBWithId(Train train) {
        Object object = new GenericService<TrainType, Integer>(TrainType.class).findByPK(train.getId());
        return object != null;
    }



    private boolean validationTrainObject(Train train, boolean isNeedToCreate) {
        errorList = new ArrayList<String>();

        if(train == null) {
            errorList.add("Unknown error occur when create object !");
            return false;
        }
        if(train.getTrainType() == null) {
            errorList.add(generateEmptyFieldMessage("trainType"));
            return false;
        }

        if (train.getId() <= 0 || train.getTrainType().getId() <= 0) {
            if(train.getId() <= 0) {
                errorList.add(generateMesssageAboutInvalidIdForField("train.getId()", train.getId()));
            } else {
                errorList.add(generateMesssageAboutInvalidIdForField("train.getTrainType().getId()",train.getTrainType().getId()));
            }
        }
        if(objectHasStoredInDBWithId(train)) {
            errorList.add("Object train with id = " + train.getId() + "already has stored !");
        }

        if (errorList.size() == 0) {
            return isNeedToCreate ? furtherValidation(train) : true;
        }
        else {
            return false;
        }
    }

    boolean furtherValidation(Train train) {
        boolean isValid = true;

        Train storedTrain = new TrainService().findByPK(train.getId());
        if(storedTrain != null) {
            isValid = false;
            errorList.add("Attempt to create train with existing name.");
        }

        return isValid;
    }

    private String generateMesssageAboutInvalidIdForField(String field, int invalidID) {
        return "Field " + field + " has invalid id == " + invalidID;
    }

    private String generateEmptyFieldMessage(String field) {
        return "Field " + field + " shouldn't be empty!";
    }

    private String generateMessageAboutNotExistingObjectForId(String field) {
        return "Cannot fetch object = " + field + " by entered id";
    }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
