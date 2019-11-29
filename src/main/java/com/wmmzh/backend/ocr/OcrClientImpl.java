package com.wmmzh.backend.ocr;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OcrClientImpl implements OcrClient {
    private final String API_KEY = "21579698bb88957";

    @Value("${rijad.test}")
    private String base64Image;

    @Override
    public String getTextFromImage(String base64) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.ocr.space/parse/image")
                .header("apikey", API_KEY)
                .header("accept", "application/json")
                .field("base64Image", base64Image)
                .field("language", "ger")
                .asJson();

        return response.getBody().toPrettyString();
    }
}
