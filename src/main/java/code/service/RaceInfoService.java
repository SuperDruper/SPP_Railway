package code.service;

import code.infrastructure.Constants;
import code.infrastructure.ValidationUtils;
import code.model.Race;
import code.model.RaceStation;
import code.model.race.RaceInfo;
import code.model.race.RaceSearchData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by PC-Alyaksei on 11.05.2016.
 */
public class RaceInfoService {

    private RaceSearchData raceSearchData;
    private List<RaceInfo> raceInfos;
    private List<String> errorList;

    private RaceCostService raceCostService = new RaceCostService();

    public List<RaceInfo> find (RaceSearchData data) {
        raceSearchData = data;
        if (validateSearchData()) {
            List<Race> races = new RaceService().findRaces(raceSearchData);
            raceInfos = new ArrayList<RaceInfo>();

            for(Race race : races) {
                raceInfos.add(ConvertToRaceInfo(race));
            }
        }

        return raceInfos;
    }

    private boolean validateSearchData() {
        errorList = ValidationUtils.validate(raceSearchData);

        Date raceDate = raceSearchData.getRaceDate();
        if (raceDate != null && !isInNDaysFromToday(raceDate,
                Constants.DAYS_AMOUNT_BEFORE_RACE_TO_START_TICKET_SELLING)) {
            errorList.add("Race date must be either today or within next " +
                    Constants.DAYS_AMOUNT_BEFORE_RACE_TO_START_TICKET_SELLING + " days!");
        }

        return errorList.size() == 0;
    }

    private static boolean isInNDaysFromToday(Date date, int n) {
        Calendar thatDay = Calendar.getInstance();
        thatDay.setTime(date);

        Calendar nDaysFromToday = Calendar.getInstance();
        nDaysFromToday.add(Calendar.DAY_OF_MONTH, n + 1);

        LocalDateTime now = LocalDateTime.now(); // current date and time
        LocalDateTime todayLocal = now.toLocalDate().atStartOfDay();
        Date todayDate = Date.from(todayLocal.atZone(ZoneId.systemDefault()).toInstant());
        Calendar today = Calendar.getInstance();
        today.setTime(todayDate);

        return thatDay.compareTo(today) >= 0 && thatDay.before(nDaysFromToday);
    }


    private RaceInfo ConvertToRaceInfo(Race race) {
        RaceStation departureRaceStation = getRaceStationFromListById(
                race.getRaceStations(), raceSearchData.getDepartureStationId());
        RaceStation arrivingRaceStation  = getRaceStationFromListById(
                race.getRaceStations(), raceSearchData.getArriveStationId());

        Date departure = departureRaceStation.getDepature();
        Date arriving =  arrivingRaceStation.getArriving();
        double cost = raceCostService.calculateCost(
                race.getTrain().getTrainType().getCoefficient(),
                departureRaceStation.getStation().getId(),
                arrivingRaceStation.getStation().getId()
        );

        return new RaceInfo (
                race.getId(),
                race.getRoute().getName(),
                departure,
                arriving,
                cost
        );
    }

    private RaceStation getRaceStationFromListById(Collection<RaceStation> list, int stationId) {
        RaceStation result = null;

        for (RaceStation raceStation : list) {
            if (raceStation.getStation().getId() == stationId) {
                result = raceStation;
                break;
            }
        }

        return result;
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
