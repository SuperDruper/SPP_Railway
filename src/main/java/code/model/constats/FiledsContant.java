package code.model.constats;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class FiledsContant {
    public static final String kName = "name";
    public static final String kID = "id";

    public enum kAction {
        kCREATE(0),
        kUPDATE(1),
        kDELETE(2);

        private final int id;
        kAction(int id) { this.id = id; }
        public int getValue() { return id; }
    }
}
