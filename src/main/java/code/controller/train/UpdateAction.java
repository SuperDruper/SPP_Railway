package code.controller.train;

import code.controller.PostAction;
import code.infrastructure.ValidationUtils;
import code.model.*;
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
        errorList =  new ArrayList<String>();
        try {
            new TrainService().delete(train);
        } catch (Exception exc) {
            errorList.add("Cannot delete entity, 'cause it's already related with another object !");
        }
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
        Object object = new GenericService<Train, Integer>(Train.class).getModelByUniqueStringField("train_number", train.getTrain_number());
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

        if(objectHasStoredInDBWithId(train)) {
            errorList.add("Train with number = " + train.getTrain_number() + " already exist");
        }

        if (errorList.size() == 0) {
            return isNeedToCreate ? furtherValidation(train) : true;
        }
        else {
            return false;
        }
    }



    private final String trainNumber = "train_number";

    boolean furtherValidation(Train train) {
        boolean isValid = true;

        Train storedTrain = new TrainService().getModelByUniqueStringField(trainNumber, train.getId());
        if(storedTrain != null) {
            isValid = false;
            errorList.add("Attempt to create train with existing number!");
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
