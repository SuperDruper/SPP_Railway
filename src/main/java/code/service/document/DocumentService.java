package code.service.document;

import au.com.bytecode.opencsv.CSVWriter;
import code.infrastructure.DateUtils;
import code.infrastructure.DocumentFormat;
import code.model.*;
import code.model.race.RaceFullData;
import code.model.race.RaceInfo;
import code.model.race.RaceSearchData;
import code.model.ticket.TicketDetails;
import code.service.*;

import java.io.*;
import java.util.List;

/**
 * Created by PC-Alyaksei on 11.05.2016.
 */
public class DocumentService {

    public static ByteArrayOutputStream generateRaceInfoList(
            RaceSearchData searchData, DocumentFormat format) throws IOException {
        RaceInfoService service = new RaceInfoService();

        List<RaceInfo> raceInfos = service.find(searchData);

        switch (format) {
            case XLS:
                return XlsDocumentsGenerationService.generateRaceInfoList(raceInfos);
            case CSV:
                return CsvDocumentsGenerationService.generateRaceInfoList(raceInfos);
            case PDF:
                StationService stationService = new StationService();

                Station stationFrom = stationService.findByPK(searchData.getDepartureStationId());
                Station stationTo = stationService.findByPK(searchData.getArriveStationId());

                String stationFromName = stationFrom.getName();
                String stationToName = stationTo.getName();

                return PdfDocumentsGenerationService.generateRaceInfoList(raceInfos,
                        searchData.getRaceDate(), stationFromName, stationToName);
        }

        return null;
    }

    public static ByteArrayOutputStream generateTicket(int ticketId, DocumentFormat format) throws IOException {
        TicketService service = new TicketService();

        TicketDetails details = service.findTicketDetailsByPK(ticketId);

        switch (format) {
            case XLS:
                return XlsDocumentsGenerationService.generateTicket(details);
            case CSV:
                return CsvDocumentsGenerationService.generateTicket(details);
            case PDF:
                return PdfDocumentsGenerationService.generateTicket(details);
        }

        return null;
    }

    public static ByteArrayOutputStream generateRaceFullData(int raceId, DocumentFormat format) throws IOException {
        RaceService raceService = new RaceService();
        RaceStationService raceStationService = new RaceStationService();

        Race race = raceService.findByPKWithDetails(raceId);
        RaceStation departureRaceStation = raceStationService.getDepartureRaceStationByRacePK(raceId);
        RaceStation arriveRaceStation = raceStationService.getArriveRaceStationByRacePK(raceId);

        Train train = race.getTrain();

        int placesInCarriage = train.getTrainType().getPlacesAmount();
        int carriageAmount = train.getCarriageAmount();
        int ticketsSold = race.getTickets().size();
        int placesLeft = placesInCarriage * carriageAmount - ticketsSold;

        RaceFullData data = new RaceFullData(raceId, train.getId(), race.getRoute().getName(),
                departureRaceStation.getStation().getName(), arriveRaceStation.getStation().getName(),
                departureRaceStation.getDepature(), arriveRaceStation.getArriving(), carriageAmount, placesLeft);

        switch (format) {
            case XLS:
                return XlsDocumentsGenerationService.generateRaceFullData(data);
            case CSV:
                return CsvDocumentsGenerationService.generateRaceFullData(data);
            case PDF:
                return PdfDocumentsGenerationService.generateRaceFullData(data);
        }

        return null;
    }

    public static ByteArrayOutputStream generateRaceStations(
            int raceId, DocumentFormat format) throws IOException {
        List<RaceStation> list = new RaceStationService().getRaceStationsByRaceId(raceId);

        switch (format) {
            case XLS:
                return XlsDocumentsGenerationService.generateRaceStations(list);
            case CSV:
                return CsvDocumentsGenerationService.generateRaceStations(list);
            case PDF:
                return PdfDocumentsGenerationService.generateRaceStations(list, raceId);
        }

        return null;
    }

    public static ByteArrayOutputStream generateRouteFilling (
            int routeId, DocumentFormat format) throws IOException {
        Route route = new RouteService().findByPK(routeId);
        String routeName = route.getName();

        List<Race> list = new RaceService().getRaceWithDetailsByRouteId(routeId);

        switch (format) {
            case XLS:
                return XlsDocumentsGenerationService.generateRouteFilling(list, routeName);
            case CSV:
                return CsvDocumentsGenerationService.generateRouteFilling(list, routeName);
            case PDF:
                return PdfDocumentsGenerationService.generateRouteFilling(list, routeName);
        }

        return null;
    }
}
