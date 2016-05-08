package code.controller.race_station;

import code.controller.PostAction;
import code.controller.race_station.model.RaceStationContainer;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.*;
import code.service.GenericService;
import code.service.RaceService;
import code.service.RaceStationService;
import code.service.StationService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

/**
 * Created by dzmitry.antonenka on 26.04.2016.
 */
public class UpdateAction extends PostAction {
    private CrudAction action;
    private RaceStation raceStation;
    private List<String> errorList = new ArrayList();
    RaceStationContainer raceStationContainer;

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
        raceStation.setRace(new RaceService().findByPKWithDetails(raceStation.getRace().getId()));
        raceStation.setStation(new GenericService<Station, Integer>(Station.class).findByPK(raceStation.getStation().getId()));

        errorList = validate(raceStation, true);
        if(!errorList.isEmpty()) return;

        new GenericService<RaceStation, Integer>(RaceStation.class).persist(raceStation);
    }

    void updateActionExecute()
    {
        raceStation.setRace(new RaceService().findByPKWithDetails(raceStation.getRace().getId()));
        raceStation.setStation(new GenericService<Station, Integer>(Station.class).findByPK(raceStation.getStation().getId()));

        errorList = validate(raceStation, false);
        if(!errorList.isEmpty()) return;

        new GenericService<RaceStation, Integer>(RaceStation.class).update(raceStation);
    }

    void deleteActionExecute() {
        errorList = new ArrayList();
        try {
            new GenericService<RaceStation, Integer>(RaceStation.class).deleteByPK(raceStation.getId());
        } catch (Exception exc) {
            errorList.add("Cannot delete entity 'cause it related with another object");
        }
    }

    public List<String> validate(RaceStation raceStation, boolean isNeedToCreate) {
        List<String> errorList = new ArrayList();

        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<RaceStation>> set = validator.validate(raceStation);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidationTicketsForRace(errorList, raceStation, isNeedToCreate);
        }
        else {
            return errorList;
        }
    }

    // HELPERS
    public List<String> furtherValidationTicketsForRace(List<String> errorList, RaceStation raceStation, boolean isNeedToCreate) {
        boolean approved = true;

        RaceStation storedDuplicate = new RaceStationService().findByPK(raceStation.getId());

        if(isNeedToCreate && storedDuplicate != null) {
            errorList.add("Attempt to add duplicate");
        }

        return errorList;
    }


    public RaceStationContainer getRaceStationContainer() {
        return raceStationContainer;
    }

    public void setRaceStationContainer(RaceStationContainer raceStationContainer) {
        this.raceStationContainer = raceStationContainer;
        this.raceStation = raceStationContainer.getRaceStationObjectFromCurrentContainer();
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public CrudAction getAction() {
        return action;
    }
    public void setAction(CrudAction action) {
        this.action = action;
    }
}
