package helpers;

import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import models.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class General {
    public static <T> String ObjectToJson(List<T> dataList){
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(dataList);
        return jsonString;
    }
    
    public static <T> String ObjectToJson(T object){
        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(object);
        return jsonString;
    }
    
    public static void sendAsJson( HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        out.print(json);
        out.flush();
    }
}
