package code.controller.user;

import code.model.User;
import com.opensymphony.xwork2.ActionSupport;
import code.model.Role;
import code.service.RoleService;
import code.service.UserService;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private User user;


    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String register() throws Exception {
        try {
            Role role = new RoleService().getRoleByName("User");
            user.setRole(role);
            new UserService().persist(user);
        } catch (Exception ex) {
            System.out.println("********************");
            ex.printStackTrace();
            System.out.println("********************");
            return ERROR;
        }
        return SUCCESS;
    }


    public void throwException() throws Exception {
        throw new Exception("Exception thrown from throwException");
    }

    public void throwNullPointerException() throws NullPointerException {
        throw new NullPointerException("Null Pointer Exception thrown from "
                + RegisterAction.class.toString());
    }

    public void throwSecurityException() throws SecurityBreachException {
        throw new SecurityBreachException(
                "Security breach exception thrown from throwSecurityException");
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

