package com.esliceu.PracticaDrawing2SpringBoot.Controllers;
import com.esliceu.PracticaDrawing2SpringBoot.Entities.Figure;
import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.springframework.stereotype.Controller;

        import java.util.List;
@Controller
public class ObjectCounter {
    public int countFiguresInJson(String strokJson,String figureJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Figure figures = objectMapper.readTree(figureJson);
            JsonNode jsonNode = objectMapper.readTree(strokJson);

            if (jsonNode.isArray()) {
                return jsonNode.size();
            } else {
                return 0;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}