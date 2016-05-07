package code.controller.station;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Role;
import code.model.Route;
import code.model.Station;
import code.service.GenericService;
import code.service.RoleService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Station station;
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
        if(!validate(station, true)) return;

        new GenericService<Station, Integer>(Station.class).persist(station);
    }

    void updateActionExecute()
    {
        if(!validate(station, false)) return;
        new GenericService<Station, Integer>(Station.class).update(station);
    }

    void deleteActionExecute() {
        errorList =  new ArrayList<>();
        try {
            new GenericService<Station, Integer>(Station.class).delete(station);
        } catch (Exception exc) {
            errorList.add("Cannot delete entity, 'cause it's already related with another object !");
        }
    }

    private boolean validate(Station station, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Station>> set = validator.validate(station);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidation(station, isNeedToCreate);
        } else {
            return false;
        }
    }
    private boolean furtherValidation(Station station, boolean isNeedToCreate) {
        Station storedRoleWithDuplicatedName =
                        new GenericService<Station, Integer>(Station.class).getModelByUniqueStringField("name", station.getName());
        if (storedRoleWithDuplicatedName != null) {
            String message = isNeedToCreate ? "Attempt to create station with existing name !" : "Attempt to change name for station with existing one.";
            errorList.add(message);
            return false;
        } else {
            return true;
        }
    }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }
}
