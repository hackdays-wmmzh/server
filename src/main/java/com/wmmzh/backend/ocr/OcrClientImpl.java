package com.wmmzh.backend.ocr;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class OcrClientImpl implements OcrClient {
    private final String API_KEY = "21579698bb88957";

    @Override
    public String getTextFromImage(String base64) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.ocr.space/parse/image")
                .header("apikey", API_KEY)
                .header("accept", "application/json")
                .field("base64Image", base64)
                .field("language", "ger")
                .asJson();


        if (!response.isSuccess()) {
            return response.toString();
        }

        Gson gson = new Gson();
        OcrModel orcModel =  gson.fromJson(response.getBody().toString(), OcrModel.class);

        StringBuilder responseBuilder = new StringBuilder();
        for (OcrModel.ParsedResult parsedResult : orcModel.getParsedResults()) {
            responseBuilder.append(" ").append(parsedResult.getParsedText());
        }

        return responseBuilder.toString();
    }
}
