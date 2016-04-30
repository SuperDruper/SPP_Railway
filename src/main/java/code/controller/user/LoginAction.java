package code.controller.user;

import code.controller.PostAction;
import code.model.User;
import code.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class LoginAction extends PostAction {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private String userRoleName;
    private List<String> errorList;


    @Override
    public String create() {
        if (login == null || password == null) {
            errorList = new ArrayList<String>(){{
                add("Login and password can't be null!");
            }};
        } else {
            User user = new UserService().getUserByLogin(login);

            if (user == null || !user.getPassword().equals(password)) {
                errorList = new ArrayList<String>(){{
                    add("Such pair of login and password does not exists!");
                }};
            }
            else {
                setUserIdToSession(user.getId());
                userRoleName = user.getRole().getName();
            }
        }

        return SUCCESS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}


