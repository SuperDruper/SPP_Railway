package code.controller.user;

import com.opensymphony.xwork2.ActionSupport;
import code.service.UserService;
import code.model.User;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserListShowingAction extends ActionSupport {

    private List<User> users;



    @Override
    public String execute() throws Exception {
        return view();
    }
    // I don't know why, but when i try to access on url that connected with this action
    // it response i can't find method  view in this class, so it was created!
    public String view() throws Exception {
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
