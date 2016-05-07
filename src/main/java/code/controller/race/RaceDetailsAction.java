package code.controller.race;

import code.controller.PostAction;
import code.controller.shared.Authorize;
import code.model.Race;
import code.model.Ticket;
import code.model.race.Carriage;
import code.model.race.Place;
import code.model.race.RaceDetails;
import code.service.RaceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Alyaksei on 02.05.2016.
 */
@Authorize
public class RaceDetailsAction extends PostAction {

    private int raceId;
    private RaceDetails raceDetails;

    private List<String> errorList = new ArrayList<>();



    @Override
    public String create() throws Exception {
        Race race = new RaceService().findByPKWithDetails(raceId);
        if (raceId > 0 && race != null) {
            convertToRaceDetails(race);
        }
        else {
            errorList.add("Race you chose was not found!");
        }
        return SUCCESS;
    }

    private void convertToRaceDetails(Race race) {
        int placesInCarriage = race.getTrain().getTrainType().getPlacesAmount();
        int carriageAmount = race.getTrain().getCarriageAmount();
        List<Ticket> tickets = new ArrayList<>(race.getTickets());

        List<Carriage> carriages = new ArrayList<>();
        for (int i = 1; i <= carriageAmount; i++) {
            List<Place> places = new ArrayList<>();

            for (int j = 1; j <= placesInCarriage; j++) {
                boolean ticketFound = false;

                for (Ticket t : tickets) {
                    if (t.getCarriageNum() == i && t.getNum() == j) {
                        ticketFound = true;
                        break;
                    }
                }

                if (!ticketFound) {
                    Place place = new Place(j);
                    places.add(place);
                }
            }

            Carriage car = new Carriage(i, places);
            carriages.add(car);
        }

        raceDetails = new RaceDetails();
        raceDetails.setCarriages(carriages);
    }


    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public RaceDetails getRaceDetails() {
        return raceDetails;
    }

    public void setRaceDetails(RaceDetails raceDetails) {
        this.raceDetails = raceDetails;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

}
