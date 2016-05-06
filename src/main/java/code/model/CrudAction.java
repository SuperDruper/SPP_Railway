package code.model;

import java.io.Serializable;

/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
public class CrudAction implements Serializable {
    private int id;

    public static int CUSTOM_ACTION = 10;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}
