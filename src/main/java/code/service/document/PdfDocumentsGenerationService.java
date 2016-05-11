package code.service.document;

import au.com.bytecode.opencsv.CSVWriter;
import code.infrastructure.DateUtils;
import code.model.Race;
import code.model.RaceStation;
import code.model.Train;
import code.model.race.RaceFullData;
import code.model.race.RaceInfo;
import code.model.ticket.TicketDetails;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PC-Alyaksei on 08.05.2016.
 */
public class PdfDocumentsGenerationService {

    private static final int FONT_SIZE_SMALL = 10;
    private static final int FONT_SIZE_NORMAL = 14;
    private static final int FONT_SIZE_BIG = 20;
    private static final int VERTICAL_SPACE_TINY = 5;
    private static final int VERTICAL_SPACE_SMALL = 20;
    private static final int VERTICAL_SPACE_MEDIUM = 50;
    private static final int VERTICAL_SPACE_BIG = 80;
    private static final int HEIGHT_SMALL_LINE = FONT_SIZE_SMALL + 1;
    private static final int HEIGHT_NORMAL_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY + 2;
    private static final int HEIGHT_BIG_LINE = FONT_SIZE_NORMAL + VERTICAL_SPACE_TINY;

    private static final String FONT_PATH = "/times.ttf";

    private static final Font FONT_FOR_OBJECT_NAME = FontFactory.getFont(
            FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20, Font.BOLD);
    private static final Font COMMON_FONT = FontFactory.getFont(
            FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20);
    public static final String RACE_INFORMATION_LIST = "Race information list";
    public static final int TABLE_WIDTH_PERCENTAGE = 100;
    public static final String LOGO_PNG_PATH = "/logo.png";


    public static ByteArrayOutputStream generateRaceInfoList(List<RaceInfo> raceInfos
            , Date date, String stationFrom, String stationTo) throws IOException {
        String[] headers = new String[] {
                "№", "Race", "Route", "Departure", "Arriving", "Cost"};
        String tableHeader = String.format("Table 1 - races on %s from %s to %s",
                DateUtils.humanReadableDate(date), stationFrom, stationTo);
        float[] widths = new float[] {0.5f, 1, 2, 1.6f, 1.6f, 1.4f};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = null;
        try {
            document = getStandardDocument(stream);

            PdfPTable table = prepareTableWithHeader(headers, tableHeader, RACE_INFORMATION_LIST, widths, document);

            for (int rowNum = 0; rowNum < raceInfos.size(); rowNum++) {
                RaceInfo raceInfo = raceInfos.get(rowNum);

                addStandardCell(table, String.valueOf(rowNum + 1));
                addStandardCell(table, raceInfo.getId());
                addStandardCell(table, raceInfo.getName());
                addStandardCell(table, DateUtils.humanReadableDateTime(raceInfo.getDeparture()));
                addStandardCell(table, DateUtils.humanReadableDateTime(raceInfo.getArriving()));
                addStandardCell(table, raceInfo.getCost());
            }

            document.add(table);
            document.addAuthor("Railway team");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return stream;
    }



    public static ByteArrayOutputStream generateTicket(TicketDetails ticket) {
        String[] headers = new String[] {
                "Ticket", "Race", "Route", "Departure station", "Departure",
                "Arriving station", "Arriving", "Carriage", "Place"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = null;
        String[] data = new String[] {
                "" + ticket.getTicketNum(),
                "" + ticket.getRaceId(),
                ticket.getRouteName(),
                ticket.getDepartureStationName(),
                DateUtils.humanReadableDateTime(ticket.getDepartureDate()),
                ticket.getArriveStationName(),
                DateUtils.humanReadableDateTime(ticket.getArriveDate()),
                "" + ticket.getCarriageNum(),
                "" + ticket.getPlaceNum()
        };

        try {
            document = getStandardDocument(stream);

            Paragraph orderNumber = new Paragraph();
            orderNumber.add(new Chunk("Ticket №", FONT_FOR_OBJECT_NAME));
            orderNumber.add(new Chunk(data[0], COMMON_FONT));
            orderNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(orderNumber);
            document.add(Chunk.NEWLINE);

            for (int i = 1; i < data.length; i++) {
                addStandardParagraphWithNewLine(document, data[i], headers[i] + ": ");
            }
//            addStandardParagraphWithNewLine(document, data[1], "Race: ");
//            addStandardParagraphWithNewLine(document, data[2], "Route: ");
//            addStandardParagraphWithNewLine(document, data[3], "Departure station: ");
//            addStandardParagraphWithNewLine(document, data[4], "Departure date: ");
//            addStandardParagraphWithNewLine(document, data[5], "Arriving station: ");
//            addStandardParagraphWithNewLine(document, data[6], "Arriving date: ");
            document.addAuthor("Railway team");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return stream;
    }


    public static ByteArrayOutputStream generateRaceFullData(RaceFullData race) {
        String[] headers = new String[] {
                "Race", "Train", "Route", "Departure station", "Departure",
                "Arriving station", "Arriving", "Carriages", "Left places"};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = null;
        String[] data = new String[]{
                "" + race.getRaceId(),
                "" + race.getTrainNum(),
                race.getRouteName(),
                race.getDepartureStationName(),
                DateUtils.humanReadableDateTime(race.getDepartureDate()),
                race.getArriveStationName(),
                DateUtils.humanReadableDateTime(race.getArriveDate()),
                "" + race.getCarriages(),
                "" + race.getPlacesLeft()
        };

        try {
            document = getStandardDocument(stream);

            Paragraph orderNumber = new Paragraph();
            orderNumber.add(new Chunk("Information about race № ", FONT_FOR_OBJECT_NAME));
            orderNumber.add(new Chunk(data[0], COMMON_FONT));
            orderNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(orderNumber);
            document.add(Chunk.NEWLINE);

            for (int i = 1; i < data.length; i++) {
                addStandardParagraphWithNewLine(document, data[i], headers[i] + ": ");
            }

            document.addAuthor("Railway team");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }

        return stream;
    }

    public static ByteArrayOutputStream generateRaceStations(List<RaceStation> stations, int race) throws IOException {
        String[] headers = new String[] {
                "№", "Station", "Departure", "Arriving"};
        String tableHeader = String.format("Table 1 - station list");
        String header = String.format("List of stations on race №%s", race);
        float[] widths = new float[] {0.5f, 1.6f, 1.6f, 1.6f};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = null;
        try {
            document = getStandardDocument(stream);

            PdfPTable table = prepareTableWithHeader(headers,
                    tableHeader, header, widths, document);

            for (int rowNum = 0; rowNum < stations.size(); rowNum++) {
                RaceStation station = stations.get(rowNum);

                addStandardCell(table, String.valueOf(rowNum + 1));
                addStandardCell(table, station.getStation().getName());
                addStandardCell(table, station.getDepature() == null ? "finish"
                        : DateUtils.humanReadableDateTime(station.getDepature()));
                addStandardCell(table, station.getArriving() == null ? "start"
                        : DateUtils.humanReadableDateTime(station.getArriving()));
            }

            document.add(table);
            document.addAuthor("Railway team");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateRouteFilling (List<Race> races, String routeName) throws IOException {
        String[] headers = new String[] {
                "№", "Race", "Train", "Tickets", "Filling (%)"};
        String header = String.format("Filling for route %s", routeName);
        String tableHeader = String.format("Table 1 - route filling");
        float[] widths = new float[] {0.5f, 0.65f, 0.8f, 1f, 1f};

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Document document = null;
        try {
            document = getStandardDocument(stream);
            PdfPTable table = prepareTableWithHeader(headers,
                    tableHeader, header, widths, document);

            for (int rowNum = 0; rowNum < races.size(); rowNum++) {
                Race race = races.get(rowNum);
                Train train = race.getTrain();

                addStandardCell(table, rowNum + 1);
                addStandardCell(table, race.getId());
                addStandardCell(table, race.getTrain().getId());
                addStandardCell(table, race.getTickets().size());
                float filling = race.getTickets().size() * 100.0f /
                        (train.getCarriageAmount() * train.getTrainType().getPlacesAmount());
                float filling2DigitsAfterDot = (float) Math.floor(filling * 100) / 100;
                addStandardCell(table, filling2DigitsAfterDot);
            }

            document.add(table);
            document.addAuthor("Railway team");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return stream;
    }




    private static void addStandardCell(PdfPTable table, String content) {
        table.addCell(new Phrase(new Chunk(content, COMMON_FONT)));
    }
    private static void addStandardCell(PdfPTable table, Number content) {
        addStandardCell(table, "" + content);
    }


    private static PdfPTable prepareTableWithHeader(String[] headers, String tableHeader, String header
            , float[] widths, Document document) throws DocumentException {
        doHeader(document, header);
        Paragraph paragraph;


        paragraph = new Paragraph();
        paragraph.setFont(FONT_FOR_OBJECT_NAME);
        paragraph.setSpacingAfter(VERTICAL_SPACE_TINY);
        paragraph.add(new Chunk(tableHeader));
        document.add(paragraph);

        PdfPTable table = new PdfPTable(headers.length);
        table.setSpacingBefore(VERTICAL_SPACE_TINY);
        table.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
        if (widths != null) table.setWidths(widths);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        for (String tableColumnHeader : headers) {
            addStandardCell(table, tableColumnHeader);
        }
        return table;
    }

    private static void doHeader(Document document, String header) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(FONT_FOR_OBJECT_NAME);
        paragraph.setSpacingAfter(VERTICAL_SPACE_TINY);
        paragraph.add(new Chunk(header));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
    }


    private static Document getStandardDocument(ByteArrayOutputStream stream) throws DocumentException {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, stream);
        document.open();
        addWaterMark(pdfWriter);
        return document;
    }


    private static void addWaterMark(PdfWriter writer) {
        Phrase watermark = new Phrase("Railway team", FontFactory.getFont(FontFactory.HELVETICA, 40,
                Font.BOLD, BaseColor.GRAY));
        Rectangle pageSize = writer.getPageSize();
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, x, y, 45);

        Image image;
        try {
            image = Image.getInstance(System.class.getResource(LOGO_PNG_PATH));
            image.setAbsolutePosition(
                    PageSize.A4.getWidth() - image.getScaledWidth() - 5, 5);
            //image.scaleAbsolute(600, 250);
            canvas.addImage(image);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }


    private static void addStandardParagraphWithNewLine(
            Document document, String content, String title) throws DocumentException {
        addStandardParagraph(document, content, title);
        document.add(Chunk.NEWLINE);
    }

    private static void addStandardParagraph(Document document, String content, String title) throws DocumentException {
        Paragraph departureDate = new Paragraph();
        departureDate.add(new Chunk(title , FONT_FOR_OBJECT_NAME));
        departureDate.add(new Chunk(content, COMMON_FONT));
        document.add(departureDate);
    }

}
