package code.controller.document;

import code.controller.GetAction;
import code.infrastructure.DocumentFormat;
import code.service.document.DocumentService;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by PC-Alyaksei on 11.05.2016.
 */
public class RaceDocumentsController extends GetAction implements ServletResponseAware {

    private HttpServletResponse response;

    private int id;
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

        switch (format) {
            case XLS:
                contentType = "application/xls";
                fileName = "race.xls";
                break;
            case CSV:
                contentType = "application/csv";
                fileName = "race.csv";
                break;
            case PDF:
                contentType = "application/pdf";
                fileName = "race.pdf";
                break;
        }

        makeResponse(DocumentService.generateRaceFullData(id, format), contentType, fileName);
        return NONE;
    }



    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    public DocumentFormat getFormat() {
        return format;
    }

    public void setFormat(DocumentFormat format) {
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}