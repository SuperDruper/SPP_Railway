package code.controller.user;

import code.controller.GetAction;
import code.model.User;
import code.service.UserService;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 *
 */
public class GetProfileAction extends GetAction {

    private static final long serialVersionUID = 1L;

    private User user;

    @Override
    public String view() throws Exception{
        if (getUserIdFromSession() != null) {
            user = new UserService().findByPK(getUserIdFromSession());
            setUserToSession(user);
        }

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

