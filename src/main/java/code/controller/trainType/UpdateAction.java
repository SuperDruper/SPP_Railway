package code.controller.trainType;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Train;
import code.model.TrainType;
import code.service.GenericService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private TrainType trainType;

    private List<String> errorList;

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

    private boolean validate(TrainType trainType, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<TrainType>> set = validator.validate(trainType);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            if(isNeedToCreate) {
                List<TrainType> trainTypes = new GenericService<TrainType, Integer>(TrainType.class).getModelListByStringField("name", trainType.getName());
                if(!(trainTypes == null || trainTypes.isEmpty()))
                {
                    errorList.add("Cannot add train type with such name, because it's already exist !");
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        else {
            return false;
        }

        return false;
    }

    void saveActionExecute() {
        if(!validate(trainType, true)) return;

        new GenericService<TrainType, Integer>(TrainType.class).persist(trainType);
    }
    void updateActionExecute()
    {
        if(!validate(trainType, false)) {
            new GenericService<TrainType, Integer>(TrainType.class).update(trainType);
        }
    }
    void deleteActionExecute() {
        errorList =  new ArrayList();
        try {
            new GenericService<TrainType, Integer>(TrainType.class).delete(trainType);
        } catch (Exception exc) {
            errorList.add("Cannot delete entity, 'cause it's already related with another object !");
        }
    }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public TrainType getTrainType() { return trainType; }
    public void setTrainType(TrainType trainType) { this.trainType = trainType; }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
