package code.controller.user;

import code.controller.PostAction;
import code.model.User;
import code.model.Role;
import code.service.RoleService;
import code.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class RegisterAction extends PostAction {

    private static final long serialVersionUID = 1L;
    public static final int PASSWORD_CHAR_MIN_COUNT = 3;

    private User user;
    private List<String> errorList;


    @Override
    public String create() {
        Role role = RoleService.getUserRole();
        user.setRole(role);

        if (validate(user)) {
            new UserService().persist(user);
        }

        return SUCCESS;
    }

    private boolean validate(User user) {
        errorList = new ArrayList<String>();

        if (user.getLogin() == null || user.getLogin() == "") {
            errorList.add(generateEmptyFieldMessage("login"));
        }

        if (new UserService().getUserByLogin(user.getLogin()) != null) {
            errorList.add("User with such login is already exists!");
        }

        if (user.getEmail() == null || user.getEmail() == "") {
            errorList.add(generateEmptyFieldMessage("email"));
        }

        if (user.getPassword() == null || user.getPassword().length() < PASSWORD_CHAR_MIN_COUNT) {
            errorList.add("Password length must be more, than 7 chars!");
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

