package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRouteDao;
import code.helpers.StringHelper;
import code.infrastructure.ValidationUtils;
import code.model.Route;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RouteService extends GenericService<Route, Integer> {
    private IRouteDao dao;

    public RouteService() {
        super(Route.class);
    }

    @Override
    public IRouteDao getDao() {
        if(dao == null) {
            dao = (IRouteDao) AbstractDaoFactory.getImplDao(Route.class);
        }

        return dao;
    }

    public static List<String> validate(Route route, boolean isNeedToCreate) {
        List<String> errorList = new ArrayList();

        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<Route>> set = validator.validate(route);
        errorList = ValidationUtils.fromConstraintViolationSetToMessageList(set);

        if (errorList.size() == 0) {
            return furtherValidation(errorList, route, isNeedToCreate);
        }

        return errorList;
    }
    public static List<String> furtherValidation(List<String> errorList, Route route, boolean isNeedToCreate) {
        String fieldValue = StringHelper.getUTF8EncodedStringFromString(route.getName());
        Route storedRouteWithDuplicatedName = new RouteService().getRouteByName(fieldValue);
        if(storedRouteWithDuplicatedName != null) {
            String message = isNeedToCreate ? "Attempt to create role with existing name !" : "Attempt to change name for role with existing one.";
            errorList.add(message);
        }

        return errorList;
    }


    public Route getRouteByName(String name) {
        return getModelByUniqueStringField("name", name);
    }
}
