package code.interceptors;

import code.controller.shared.Authorize;
import code.infrastructure.Constants;
import code.infrastructure.exception.NotAuthorizedException;
import code.model.User;
import code.service.UserService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by PC-Alyaksei on 29.04.2016.
 */
public class AuthorizeInterceptor extends AbstractInterceptor {

    public static final String YOU_MUST_BE_AUTHORIZED = "You must be authorized!";

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Object action = actionInvocation.getAction();
        Class actionClass = action.getClass();
        Authorize annotation = (Authorize) actionClass.getAnnotation(Authorize.class);

        if (annotation == null) {
            return actionInvocation.invoke();
        }
        else {
            Map<String, Object> session = actionInvocation.getInvocationContext().getSession();

            Integer userId = (Integer) session.get(Constants.USER_AUTHORIZATION_SESSION_KEY);

            User user = new UserService().findByPK(userId);
            throwNotAuthorizedExceptionIfNull(user);

            session.put(Constants.USER_SESSION_KEY, user);
            String roleName = user.getRole().getName();

            String[] haveAccessRightRoleNames = annotation.value();

            if (haveAccessRightRoleNames.length == 0 ||
                    Arrays.asList(haveAccessRightRoleNames).contains(roleName)) {
                return actionInvocation.invoke();
            }

            throw new NotAuthorizedException(YOU_MUST_BE_AUTHORIZED);
        }
    }

    private void throwNotAuthorizedExceptionIfNull(Object obj) throws NotAuthorizedException {
        if (obj == null) {
            throw new NotAuthorizedException(YOU_MUST_BE_AUTHORIZED);
        }
    }

}
