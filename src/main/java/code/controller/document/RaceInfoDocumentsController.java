package code.controller.document;

import code.controller.GetAction;
import code.controller.PostAction;
import code.infrastructure.DocumentFormat;
import code.model.race.RaceSearchData;
import code.service.document.DocumentService;
import org.apache.struts2.interceptor.ServletResponseAware;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by PC-Alyaksei on 11.05.2016.
 */
public class RaceInfoDocumentsController extends GetAction implements ServletResponseAware {

    private HttpServletResponse response;


    private int from;
    private int to;
    private long timestamp;

    private RaceSearchData data;
    private DocumentFormat format;


    private void makeResponse(ByteArrayOutputStream stream, String contentType, String fileName) throws IOException {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);
        response.setContentLength(stream.size());

        OutputStream os = response.getOutputStream();
        os.write(stream.toByteArray());
        os.flush();
        os.close();
        stream.reset();
    }


    @Override
    public String view() throws Exception {
        String contentType = "";
        String fileName = "";

        data = new RaceSearchData();
        data.setArriveStationId(to);
        data.setDepartureStationId(from);
        data.setRaceDate(new Date(timestamp));

        switch (format) {
            case XLS:
                contentType = "application/xls";
                fileName = "races.xls";
                break;
            case CSV:
                contentType = "application/csv";
                fileName = "races.csv";
                break;
            case PDF:
                contentType = "application/pdf";
                fileName = "races.pdf";
                break;
        }

        makeResponse(DocumentService.generateRaceInfoList(data, format), contentType, fileName);
        return NONE;
    }


    public String getOrderPDF() throws IOException {
        return NONE;
    }


    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RaceSearchData getData() {
        return data;
    }

    public void setData(RaceSearchData data) {
        this.data = data;
    }

    public DocumentFormat getFormat() {
        return format;
    }

    public void setFormat(DocumentFormat format) {
        this.format = format;
    }
}