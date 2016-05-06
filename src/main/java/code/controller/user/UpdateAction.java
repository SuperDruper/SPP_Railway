package code.controller.user;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.controller.shared.CRUDAction;
import code.infrastructure.ValidationUtils;
import code.model.CrudAction;
import code.model.User;
import code.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by PC-Alyaksei on 24.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private User user;
    private List<String> errorList;


    @Override
    public String create() {
        switch (action.getId()) {
            case 1:
                if(validate(user)) {
                    new UserService().update(user);
                }

                break;
            case 2:
                try {
                    new UserService().deleteByPK(user.getId());
                } catch (Exception exc) {
                    errorList.add(exc.getLocalizedMessage());
                }
                break;
            default:
                errorList.add("Unsupported CRUD action type!");
                break;
        }
        return SUCCESS;
    }

    private boolean validate(User user) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            errorList = null;
            return true;
        }
        else {
            return false;
        }
    }


    public CrudAction getAction() {
        return action;
    }

    public void setAction(CrudAction action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
