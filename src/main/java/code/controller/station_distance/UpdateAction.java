package code.controller.station_distance;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Distance;
import code.model.DistancePK;
import code.model.Station;
import code.service.GenericService;
import code.service.StationDistanceService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Distance stationDistance;
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
    void saveActionExecute() {
        if(!validate(stationDistance, true)) return;

        new GenericService<Distance, Integer>(Distance.class).persist(stationDistance);
    }

    void updateActionExecute()
    {
        if(!validate(stationDistance, false)) return;
        new GenericService<Distance, Integer>(Distance.class).update(stationDistance);
    }

    void deleteActionExecute() {
        new GenericService<Distance, Integer>(Distance.class).delete(stationDistance);
    }

    private boolean validate(Distance distance, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Distance>> set = validator.validate(distance);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if(stationDistance.getStIdFrom() == stationDistance.getStIdTo()) {
            errorList.add("Attempt to create station distance with equal \'Station from\' and \'Station to\'");
        }

        if (errorList.size() == 0) {
            return furtherValidation(distance, isNeedToCreate);
        } else {
            return false;
        }
    }
    private boolean furtherValidation(Distance distance, boolean isNeedToCreate) {
        boolean isValid = true;
        DistancePK distancePK = new DistancePK(stationDistance.getStIdFrom(), stationDistance.getStIdTo());
        Distance storedDistanceWithEqualPK = null;
        try {
            storedDistanceWithEqualPK = new StationDistanceService().findByPK(distancePK);
        } catch (Exception exc) {    exc.printStackTrace();  }

        if(storedDistanceWithEqualPK != null && isNeedToCreate) {
            errorList.add("Attempt to store existing Station Distance !");
            isValid = false;
        }

        return isValid;
    }


    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setStationDistance(Distance stationDistance) {
        this.stationDistance = stationDistance;
    }
    public CrudAction getAction() {
        return action;
    }

    public void setAction(CrudAction action) {
        this.action = action;
    }
}
