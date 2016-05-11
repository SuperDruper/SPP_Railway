package code.service.document;

import code.infrastructure.DateUtils;
import code.model.Race;
import code.model.RaceStation;
import code.model.Train;
import code.model.race.RaceFullData;
import code.model.race.RaceInfo;
import code.model.ticket.TicketDetails;
import org.apache.poi.hssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-Alyaksei on 08.05.2016.
 */
public class XlsDocumentsGenerationService {

    public static ByteArrayOutputStream generateRaceInfoList(List<RaceInfo> raceInfos) throws IOException {
        String sheetName = "races";
        List<String> raceInfosHeaders = new ArrayList<String>() {{
            add("№");
            add("Race");
            add("Route");
            add("Departure");
            add("Arriving");
            add("Cost");
        }};
        int[] columnWidths = new int[] {4, 5, 30, 20, 20, 9};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = doHeader(workbook, raceInfosHeaders, sheetName);
        HSSFRow row;
        HSSFCell cell;

        for (int rowNum = 1; rowNum <= raceInfos.size(); rowNum++) {
            row = sheet.createRow(rowNum);
            RaceInfo raceInfo = raceInfos.get(rowNum - 1);
            int cellNum = 0;

            cell = row.createCell(cellNum++);
            cell.setCellValue(rowNum);

            cell = row.createCell(cellNum++);
            cell.setCellValue(raceInfo.getId());

            cell = row.createCell(cellNum++);
            cell.setCellValue(raceInfo.getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(DateUtils.humanReadableDateTime(raceInfo.getDeparture()));

            cell = row.createCell(cellNum++);
            cell.setCellValue(DateUtils.humanReadableDateTime(raceInfo.getArriving()));

            cell = row.createCell(cellNum);
            cell.setCellValue(raceInfo.getCost());
        }

        setSheetStyle(workbook, sheet, raceInfos.size());

        setColumnWidths(columnWidths, sheet);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return stream;
    }

    public static ByteArrayOutputStream generateTicket(TicketDetails ticket) throws IOException {
        String sheetName = "ticket";
        List<String> headers = new ArrayList<String>() {{
            add("Ticket");
            add("Race");
            add("Route");
            add("Departure station");
            add("Departure");
            add("Arriving station");
            add("Arriving");
            add("Carriage");
            add("Place");
        }};
        int[] columnWidths = new int[]{7, 5, 20, 18, 18, 18, 18, 9, 6};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = doHeader(workbook, headers, sheetName);
        HSSFRow row;
        HSSFCell cell;


        row = sheet.createRow(1);
        int cellNum = 0;

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getTicketNum());

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getRaceId());

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getRouteName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getDepartureStationName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(DateUtils.humanReadableDateTime(ticket.getDepartureDate()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getArriveStationName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(DateUtils.humanReadableDateTime(ticket.getArriveDate()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(ticket.getCarriageNum());

        cell = row.createCell(cellNum);
        cell.setCellValue(ticket.getPlaceNum());


        setSheetStyle(workbook, sheet, 1);

        setColumnWidths(columnWidths, sheet);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return stream;
    }


    public static ByteArrayOutputStream generateRaceFullData(RaceFullData race) throws IOException {
        String sheetName = "race";
        List<String> headers = new ArrayList<String>() {{
            add("Race");
            add("Train");
            add("Route");
            add("Departure station");
            add("Departure");
            add("Arriving station");
            add("Arriving");
            add("Carriages");
            add("Left places");
        }};
        int[] columnWidths = new int[]{5, 6, 20, 18, 18, 18, 18, 9, 11};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = doHeader(workbook, headers, sheetName);
        HSSFRow row;
        HSSFCell cell;


        row = sheet.createRow(1);
        int cellNum = 0;

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getRaceId());

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getTrainNum());

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getRouteName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getDepartureStationName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(DateUtils.humanReadableDateTime(race.getDepartureDate()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getArriveStationName());

        cell = row.createCell(cellNum++);
        cell.setCellValue(DateUtils.humanReadableDateTime(race.getArriveDate()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(race.getCarriages());

        cell = row.createCell(cellNum);
        cell.setCellValue(race.getPlacesLeft());


        setSheetStyle(workbook, sheet, 1);

        setColumnWidths(columnWidths, sheet);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return stream;
    }

    public static ByteArrayOutputStream generateRaceStations(List<RaceStation> stations) throws IOException {
        String sheetName = "races";
        List<String> headers = new ArrayList<String>() {{
            add("№");
            add("Station");
            add("Departure");
            add("Arriving");
        }};
        int[] columnWidths = new int[] {4, 30, 20, 20};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = doHeader(workbook, headers, sheetName);
        HSSFRow row;
        HSSFCell cell;

        for (int rowNum = 1; rowNum <= stations.size(); rowNum++) {
            row = sheet.createRow(rowNum);
            RaceStation station = stations.get(rowNum - 1);
            int cellNum = 0;

            cell = row.createCell(cellNum++);
            cell.setCellValue(rowNum);

            cell = row.createCell(cellNum++);
            cell.setCellValue(station.getStation().getName());

            cell = row.createCell(cellNum++);
            cell.setCellValue(station.getDepature() == null ? "finish"
                    : DateUtils.humanReadableDateTime(station.getDepature()));

            cell = row.createCell(cellNum);
            cell.setCellValue(station.getArriving() == null ? "start"
                    : DateUtils.humanReadableDateTime(station.getArriving()));
        }

        setSheetStyle(workbook, sheet, stations.size());

        setColumnWidths(columnWidths, sheet);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return stream;
    }

    public static ByteArrayOutputStream generateRouteFilling (List<Race> races, String routeName) throws IOException {
        String sheetName = routeName + " filling";
        List<String> headers = new ArrayList<String>() {{
            add("№");
            add("Race");
            add("Train");
            add("Tickets");
            add("Filling (%)");
        }};
        int[] columnWidths = new int[] {3, 8, 5, 10, 10};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = doHeader(workbook, headers, sheetName);
        HSSFRow row;
        HSSFCell cell;

        for (int rowNum = 1; rowNum <= races.size(); rowNum++) {
            row = sheet.createRow(rowNum);
            Race race = races.get(rowNum - 1);
            int cellNum = 0;

            cell = row.createCell(cellNum++);
            cell.setCellValue(rowNum);

            cell = row.createCell(cellNum++);
            cell.setCellValue(race.getId());

            cell = row.createCell(cellNum++);
            cell.setCellValue(race.getTrain().getId());

            cell = row.createCell(cellNum++);
            cell.setCellValue(race.getTickets().size());

            Train train = race.getTrain();
            cell = row.createCell(cellNum);
            cell.setCellValue(race.getTickets().size() / (double)
                    (train.getCarriageAmount() * train.getTrainType().getPlacesAmount()));

        }

        setSheetStyle(workbook, sheet, races.size());

        setColumnWidths(columnWidths, sheet);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return stream;
    }


    private static void setSheetStyle(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum) {
        HSSFRow row;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        for (int j = 0; j <= rowNum; j++) {
            row = sheet.getRow(j);
            row.setRowStyle(style);
        }
    }

    private static void setColumnWidths(int[] columnWidths, HSSFSheet sheet) {
        for (int i = 0; i < columnWidths.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, columnWidths[i] * 256);
        }
    }

    private static HSSFSheet doHeader(HSSFWorkbook workbook, List<String> header, String sheetName) {
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < header.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(new HSSFRichTextString(header.get(i)));
        }
        return sheet;
    }

}
