package code.controller.role;

import code.controller.GetAction;
import code.model.Role;
import code.model.Train;
import code.service.GenericService;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
public class RoleListShowingAction extends GetAction {
    private List<Role> roles;

    @Override
    public String view() {
        roles = new GenericService<Role, Integer>(Role.class).findAll();
        return SUCCESS;
    }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}
