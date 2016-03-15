package org.apache.struts.controller.user;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts.dao.UserDao;
import org.apache.struts.model.User;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserListShowingAction extends ActionSupport {

    public List<User> users;


    @Override
    public String execute() throws Exception {
        users = new UserDao().list();
        return SUCCESS;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
