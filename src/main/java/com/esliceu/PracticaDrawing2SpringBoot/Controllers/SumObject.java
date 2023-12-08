package com.esliceu.PracticaDrawing2SpringBoot.Controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;

@Controller
public class SumObject {
    public String sumObjectJSON(String jsonString1, String jsonString2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode1 = objectMapper.readTree(jsonString1);
            JsonNode jsonNode2 = objectMapper.readTree(jsonString2);

            ((ObjectNode) jsonNode1).setAll((ObjectNode) jsonNode2);

            return objectMapper.writeValueAsString(jsonNode1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";

        }
    }
}