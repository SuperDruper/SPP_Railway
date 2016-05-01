package code.controller.user;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.User;
import code.service.UserService;
import code.validator.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
@Authorize
public class UpdateProfileAction extends PostAction {

    private User user;
    private List<String> errorList;


    @Override
    public String create() throws Exception {
        User sessionUser = getUserFromSession();

        user.setId(sessionUser.getId());
        user.setRole(sessionUser.getRole());

        validateUser(sessionUser.getLogin());

        if (errorList.size() == 0) {
            new UserService().update(user);
        }

        return SUCCESS;
    }


    public void validateUser(String oldLogin) {
        ValidatorFactory factory = ValidationUtils.getValidationFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (!user.getLogin().equals(oldLogin) &&
                new UserService().getUserByLogin(user.getLogin()) != null) {
            errorList.add("User with such login is already exists!");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
