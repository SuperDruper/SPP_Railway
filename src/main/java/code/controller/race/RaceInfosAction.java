package code.controller.race;

import code.controller.PostAction;
import code.model.race.RaceInfo;
import code.model.race.RaceSearchData;
import code.service.RaceInfoService;

import java.util.List;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class RaceInfosAction extends PostAction {

    // Try to find data on date 2016-06-29
    private RaceSearchData raceSearchData;
    private List<RaceInfo> raceInfos;
    private List<String> errorList;

    @Override
    public String create() throws Exception {
        RaceInfoService service = new RaceInfoService();
        raceInfos = service.find(raceSearchData);
        errorList = service.getErrorList();
        return SUCCESS;
    }


    public RaceSearchData getRaceSearchData() {
        return raceSearchData;
    }

    public void setRaceSearchData(RaceSearchData raceSearchData) {
        this.raceSearchData = raceSearchData;
    }

    public List<RaceInfo> getRaceInfos() {
        return raceInfos;
    }

    public void setRaceInfos(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}