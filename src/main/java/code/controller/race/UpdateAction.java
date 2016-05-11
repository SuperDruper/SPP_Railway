package code.controller.race;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Race;
import code.model.RaceStation;
import code.model.Role;
import code.service.GenericService;
import code.service.RaceService;
import code.service.RoleService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Race race;

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
        if(!validate(race, true)) return;
        new GenericService<Race, Integer>(Race.class).persist(race);
    }

    void updateActionExecute()
    {
        if(!validate(race, false)) return;

        new GenericService<Race, Integer>(Race.class).update(race);
    }

    void deleteActionExecute() {
        errorList = new ArrayList();
        try {
            new GenericService<Race, Integer>(Race.class).delete(race);
        } catch (Exception exc) {
            errorList.add("Cannot delete entity 'cause it related with another object");
        }
    }

    private boolean validate(Race race, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Race>> set = validator.validate(race);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidation(race, isNeedToCreate);
        } else {
            return false;
        }
    }

    private final String raceNumber = "race_number";

    private boolean furtherValidation(Race race, boolean isNeedToCreate) {
        boolean isValid = true;
        Race storedRaceWithEqualId = new RaceService().getModelByUniqueStringField(raceNumber, race.getRace_number());
        if(storedRaceWithEqualId != null) {
            String message = isNeedToCreate ? "Cannot create race, 'case race with such number already exist!" : "Cannot update race number, 'cause it's already set!";
            errorList.add(message);
            isValid = false;
        }

        List<Race> racesWithCurrentRouteAndTrain =  new RaceService().findRacesWithRouteAndTrain(race.getRoute(), race.getTrain());
        if(!racesWithCurrentRouteAndTrain.isEmpty() && !(racesWithCurrentRouteAndTrain.size() == 1 && racesWithCurrentRouteAndTrain.get(0).getId() == race.getId())) {
            String message = isNeedToCreate ? "Cannot create race with already assigned train !" : "Cannot update race with already assigned train. So some race already has it and will overlap it !";
            errorList.add(message);
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

    public void setRace(Race race) { this.race = race; }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
