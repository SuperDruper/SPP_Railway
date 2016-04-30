package code.controller.user;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.User;
import code.service.UserService;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Authorize("admin")
public class UserListShowingAction extends GetAction {

    private List<User> users;

    @Override
    public String view() {
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
