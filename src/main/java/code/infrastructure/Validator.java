package code.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    private List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addError(String field, String msg) {
        HashMap error = new HashMap();
        error.put(field, msg);
        this.errors.add(error);
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }
}