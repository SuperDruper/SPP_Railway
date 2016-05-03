package code.controller.user;

import code.controller.GetAction;
import code.controller.shared.Authorize;
import code.model.Role;
import code.model.User;
import code.service.RoleService;
import code.service.UserService;

import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
@Authorize("admin")
public class UserListShowingAction extends GetAction {
    private List<User> users;
    private List<Role> roles;

    // I don't know why, but when i try to access on url that connected with this action
    // it response i can't find method  view in this class, so it was created!
    @Override
    public String view() {
        users = new UserService().findAll();
        roles = new RoleService().findAll();

        return SUCCESS;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
