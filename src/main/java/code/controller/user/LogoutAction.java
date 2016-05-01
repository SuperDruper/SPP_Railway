package code.controller.user;

import code.controller.GetAction;
import code.infrastructure.Constants;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

/**
 * Created by PC-Alyaksei on 29.04.2016.
 */
public class LogoutAction extends GetAction {

    @Override
    public String view() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.remove(Constants.USER_AUTHORIZATION_SESSION_KEY);
        session.remove(Constants.USER_SESSION_KEY);
        return SUCCESS;
    }

}
