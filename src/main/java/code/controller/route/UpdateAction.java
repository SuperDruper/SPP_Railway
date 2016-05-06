package code.controller.route;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.helpers.StringHelper;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Role;
import code.model.Route;
import code.service.GenericService;
import code.service.RoleService;
import code.service.RouteService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 24.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private Route route;
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
        if(!validate(route, true)) return;

        route.setName(StringHelper.getUTF8EncodedStringFromString(route.getName()));
        new GenericService<Route, Integer>(Route.class).persist(route);
    }

    void updateActionExecute()
    {
        if(!validate(route, false)) return;
        new GenericService<Route, Integer>(Route.class).update(route);
    }

    void deleteActionExecute() {
        new GenericService<Route, Integer>(Route.class).deleteByPK(route.getId());
    }

    private boolean validate(Route route, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Route>> set = validator.validate(route);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidation(route, isNeedToCreate);
        } else {
            return false;
        }
    }
    private boolean furtherValidation(Route route, boolean isNeedToCreate) {
        String fieldValue = StringHelper.getUTF8EncodedStringFromString(route.getName());
        Route storedRouteWithDuplicatedName = new RouteService().getRouteByName(fieldValue);
        if(storedRouteWithDuplicatedName != null) {
            String message = isNeedToCreate ? "Attempt to create role with existing name !" : "Attempt to change name for role with existing one.";
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
}
