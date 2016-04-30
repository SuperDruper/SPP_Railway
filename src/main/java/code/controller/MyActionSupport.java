package code.controller;

import code.infrastructure.Constants;
import code.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * Created by PC-Alyaksei on 30.04.2016.
 */
public class MyActionSupport extends ActionSupport {

    public static Object getFromSession(String key) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        return session.get(key);
    }
    public static void setToSession(String key, Object obj) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put(key, obj);
    }

    public static Integer getUserIdFromSession() {
        return (Integer) getFromSession(Constants.USER_AUTHORIZATION_SESSION_KEY);
    }
    public static void setUserIdToSession(Integer id) {
        setToSession(Constants.USER_AUTHORIZATION_SESSION_KEY, id);
    }

    public static User getUserFromSession() {
        return (User) getFromSession(Constants.USER_SESSION_KEY);
    }
    public static void setUserToSession(User user) {
        setToSession(Constants.USER_SESSION_KEY, user);
    }

    public static boolean isAuthorized() {
        Integer id = getUserIdFromSession();
        return id != null;
    }

}
