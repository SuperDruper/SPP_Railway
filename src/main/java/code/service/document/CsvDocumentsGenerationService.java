package code.service.document;

import au.com.bytecode.opencsv.CSVWriter;
import code.infrastructure.DateUtils;
import code.model.Race;
import code.model.RaceStation;
import code.model.Train;
import code.model.race.RaceFullData;
import code.model.race.RaceInfo;
import code.model.ticket.TicketDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by PC-Alyaksei on 09.05.2016.
 */
public class CsvDocumentsGenerationService {

    public static ByteArrayOutputStream generateRaceInfoList(List<RaceInfo> raceInfos) throws IOException {
        String[] headers = new String[] {
            "№", "Race", "Route", "Departure", "Arriving", "Cost"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("utf-8")), ',');

        writer.writeNext(headers);
        List<String[]> data = new ArrayList<>();
        
        for (int rowNum = 0; rowNum < raceInfos.size(); rowNum++) {
            RaceInfo raceInfo = raceInfos.get(rowNum);
            data.add(new String[]{
                    Integer.toString(rowNum + 1),
                    "" + raceInfo.getId(),
                    raceInfo.getName(),
                    DateUtils.humanReadableDateTime(raceInfo.getDeparture()),
                    DateUtils.humanReadableDateTime(raceInfo.getArriving()),
                    "" + raceInfo.getCost()
            });
        }

        writer.writeAll(data);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateTicket(TicketDetails ticket) throws IOException {
        String[] headers = new String[]{
                "Ticket", "Race", "Route", "Departure station", "Departure", "Arriving station", "Arriving", "Carriage", "Place"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("utf-8")), ',');
        writer.writeNext(headers);
        List<String[]> data = new ArrayList<>();


        data.add(new String[]{
                "" + ticket.getTicketNum(),
                "" + ticket.getRaceId(),
                ticket.getRouteName(),
                ticket.getDepartureStationName(),
                DateUtils.humanReadableDateTime(ticket.getDepartureDate()),
                ticket.getArriveStationName(),
                DateUtils.humanReadableDateTime(ticket.getArriveDate()),
                "" + ticket.getCarriageNum(),
                "" + ticket.getPlaceNum(),
        });

        writer.writeAll(data);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateRaceFullData(RaceFullData race) throws IOException {
        String[] headers = new String[] {
                "Race", "Train", "Route", "Departure station", "Departure",
                "Arriving station", "Arriving", "Carriages", "Left places"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("utf-8")), ',');
        writer.writeNext(headers);
        List<String[]> data = new ArrayList<>();

        data.add(new String[]{
                "" + race.getRaceId(),
                "" + race.getTrainNum(),
                race.getRouteName(),
                race.getDepartureStationName(),
                DateUtils.humanReadableDateTime(race.getDepartureDate()),
                race.getArriveStationName(),
                DateUtils.humanReadableDateTime(race.getArriveDate()),
                "" + race.getCarriages(),
                "" + race.getPlacesLeft()
        });


        writer.writeAll(data);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateRaceStations(List<RaceStation> stations) throws IOException {
        String[] headers = new String[] {
            "№", "Station", "Departure", "Arriving"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("utf-8")), ',');

        writer.writeNext(headers);
        List<String[]> data = new ArrayList<>();

        for (int rowNum = 0; rowNum < stations.size(); rowNum++) {
            RaceStation station = stations.get(rowNum);

            data.add(new String[]{
                    Integer.toString(rowNum + 1),
                    station.getStation().getName(),
                    station.getDepature() == null ? "finish"
                            : DateUtils.humanReadableDateTime(station.getDepature()),
                    station.getArriving() == null ? "start"
                            : DateUtils.humanReadableDateTime(station.getArriving())
            });
        }

        writer.writeAll(data);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateRouteFilling (List<Race> races, String routeName) throws IOException {
        String[] headers = new String[] {
            "№", "Race", "Train", "Tickets", "Filling (%)"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("utf-8")), ',');

        writer.writeNext(headers);
        List<String[]> data = new ArrayList<>();

        for (int rowNum = 0; rowNum < races.size(); rowNum++) {
            Race race = races.get(rowNum);
            Train train = race.getTrain();

            data.add(new String[]{
                    Integer.toString(rowNum + 1),
                    "" + race.getId(),
                    "" + race.getTrain().getId(),
                    "" + race.getTickets().size(),
                    "" + race.getTickets().size() / (double)
                    (train.getCarriageAmount() * train.getTrainType().getPlacesAmount())
            });
        }

        writer.writeAll(data);
        writer.close();
        return stream;
    }
}
