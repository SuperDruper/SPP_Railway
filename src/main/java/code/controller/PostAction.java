package code.controller;

/**
 * Created by PC-Alyaksei on 04.04.2016.
 *
 */
public abstract class PostAction extends MyActionSupport implements IPostAction {
    public String execute() throws Exception{
        return create();
    }
}
