package code.controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by PC-Alyaksei on 04.04.2016.
 */
public abstract class GetAction extends ActionSupport {
    public String execute() {
        return view();
    }

    public abstract String view();
}
