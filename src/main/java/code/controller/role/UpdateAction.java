package code.controller.role;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.Role;
import code.model.TrainType;
import code.service.GenericService;
import code.service.RoleService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Role role;

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
        if(!validate(role, true)) return;

        new GenericService<Role, Integer>(Role.class).persist(role);
    }

    void updateActionExecute()
    {
        if(!validate(role, false)) return;

        new GenericService<Role, Integer>(Role.class).update(role);
    }

    void deleteActionExecute() {
        errorList =  new ArrayList();
        try {
            new GenericService<Role, Integer>(Role.class).delete(role);
        } catch (Exception exc) {
            errorList.add("Cannot delete entity, 'cause it's already related with another object !");
        }
    }

    private boolean validate(Role role, boolean isNeedToCreate) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Role>> set = validator.validate(role);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidation(role, isNeedToCreate);
        } else {
            return false;
        }
    }
    private boolean furtherValidation(Role role, boolean isNeedToCreate) {
        Role storedRoleWithDuplicatedName = new RoleService().getRoleByName(role.getName());
        if(storedRoleWithDuplicatedName != null) {
            String message = isNeedToCreate ? "Attempt to create role with existing name !" : "Attempt to change name for role with existing one.";
            errorList.add(message);
            return false;
        } else {
            return true;
        }
    }

    //MARK - Getters & setters

    public void setRole(Role role) { this.role = role; }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
