package org.apache.struts.controller.user;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.service.UserService;
import org.apache.struts.model.User;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserListShowingAction extends ActionSupport {

    private List<User> users;


    @Override
    public String execute() throws Exception {
        users = new UserService().findAll();
        return SUCCESS;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
