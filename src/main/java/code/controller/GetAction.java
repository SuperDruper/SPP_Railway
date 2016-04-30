package code.controller;

/**
 * Created by PC-Alyaksei on 04.04.2016.
 *
 */
public abstract class GetAction extends MyActionSupport implements IGetAction {
    public String execute() throws Exception{
        return view();
    }
}
