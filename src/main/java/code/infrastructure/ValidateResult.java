package code.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateResult {
    public List<Map<String, String>> errors = new ArrayList<Map<String, String>>();

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
