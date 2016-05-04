package code.controller.role;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.CrudAction;
import code.model.Role;
import code.model.TrainType;
import code.service.GenericService;

/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
//@Authorize("admin")
public class UpdateAction extends PostAction {
    private CrudAction action;
    private Role role;

    @Override
    public String create() {
        switch (action.getId()) {
            case 0:
                saveActionExecute();
                break;
            case 1: // Update
                updateActionExecute();
                break;
            case 2: //Delete
                deleteActionExecute();
                break;

            default:break;
        }

        return SUCCESS;
    }
    void saveActionExecute() {
        new GenericService<Role, Integer>(Role.class).persist(role);
    }

    void updateActionExecute()
    {
        new GenericService<Role, Integer>(Role.class).update(role);
    }

    void deleteActionExecute() {
        new GenericService<Role, Integer>(Role.class).delete(role);
    }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public void setAction(CrudAction action) { this.action = action; }
    public CrudAction getAction() { return this.action; }
}
