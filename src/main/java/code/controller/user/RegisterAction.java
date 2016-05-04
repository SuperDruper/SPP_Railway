package code.controller.user;

import code.controller.PostAction;
import code.dao.hibernatedao.GenericHibernateDao;
import code.model.Role;
import code.model.User;
import code.service.GenericService;
import code.service.RoleService;
import code.service.UserService;
import code.infrastructure.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class RegisterAction extends PostAction {

    private static final long serialVersionUID = 1L;

    private User user;
    private List<String> errorList;


    @Override
    public String create() {
        //Why always const role ??
        Role role = RoleService.getUserRole();
        user.setRole(role); //replace with | new GenericService<Role, Integer>().findByPK(user.getRole().getId());

        if (validate(user)) {
            new UserService().persist(user);
        }

        return SUCCESS;
    }

    private boolean validate(User user) {
        errorList = ValidationUtils.validate(user);

        if (new UserService().getUserByLogin(user.getLogin()) != null) {
            errorList.add("User with such login is already exists!");
        }

        if (errorList.size() == 0) {
            errorList = null;
            return true;
        }
        else {
            return false;
        }
    }


    private String generateEmptyFieldMessage(String field) {
        return "Field " + field + " shouldn't be empty!";
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

