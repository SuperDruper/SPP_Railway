package tests.service.document;

import code.model.Race;
import code.model.RaceStation;
import code.model.Route;
import code.model.race.RaceFullData;
import code.model.race.RaceInfo;
import code.model.ticket.TicketDetails;
import code.service.RaceService;
import code.service.RaceStationService;
import code.service.RouteService;
import code.service.document.XlsDocumentsGenerationService;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XlsDocumentsGenerationServiceTest {

    @Test
    public void testGenerateRaceInfoList() throws Exception {
        List<RaceInfo> list = new ArrayList<RaceInfo>() {{
            add(new RaceInfo(5, "Витебск-Брест", new Date(), new Date(), 210000));
            add(new RaceInfo(655, "Витебск-Брест-Витебск", new Date(), new Date(), 4210000));
        }};

        ByteArrayOutputStream byteArrayOutputStream = XlsDocumentsGenerationService.generateRaceInfoList(list);
        try (OutputStream outputStream = new FileOutputStream("./test_documents/RaceInfoList.xls")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

    @Test
    public void testGenerateTicket() throws Exception {
        TicketDetails ticket = new TicketDetails(
                4, 1, "Витебск-Брест", "Витебск", "Брест", new Date(), new Date(), 4, 1);
        ByteArrayOutputStream byteArrayOutputStream = XlsDocumentsGenerationService.generateTicket(ticket);
        try (OutputStream outputStream = new FileOutputStream("./test_documents/Ticket.xls")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }


    @Test
    public void testGenerateRaceFullData() throws Exception {
        RaceFullData race = new RaceFullData(
                1, 1, "Витебск-Брест", "Витебск", "Брест", new Date(), new Date(), 4, 140);
        ByteArrayOutputStream byteArrayOutputStream = XlsDocumentsGenerationService.generateRaceFullData(race);
        try (OutputStream outputStream = new FileOutputStream("./test_documents/Race.xls")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

    @Test
    public void testGenerateRaceStations() throws Exception {
        List<RaceStation> list = new RaceStationService().getRaceStationsByRaceId(1);

        ByteArrayOutputStream byteArrayOutputStream = XlsDocumentsGenerationService.generateRaceStations(list);
        try (OutputStream outputStream = new FileOutputStream("./test_documents/Stations.xls")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

    @Test
    public void testGenerateRouteFilling() throws Exception {
        int routeId = 1;
        Route route = new RouteService().findByPK(routeId);
        String routeName = route.getName();

        List<Race> list = new RaceService().getRaceWithDetailsByRouteId(routeId);

        ByteArrayOutputStream byteArrayOutputStream =
                XlsDocumentsGenerationService.generateRouteFilling(list, routeName);
        try (OutputStream outputStream = new FileOutputStream("./test_documents/RouteFilling.xls")) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

}