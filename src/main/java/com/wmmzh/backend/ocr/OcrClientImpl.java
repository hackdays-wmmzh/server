package com.wmmzh.backend.ocr;

import com.google.gson.Gson;
import com.wmmzh.backend.imagga.ImaggaModel;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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


        if (!response.isSuccess()) {
            return response.toString();
        }

        Gson gson = new Gson();
        ImaggaModel orcModel =  gson.fromJson(response.getBody().toString(), ImaggaModel.class);

        return orcModel.getResult().getTags().stream()
                .max(Comparator.comparing(ImaggaModel.Tag::getConfidence))
                .map(t -> t.getTag().getDe()).orElse("Nichts");
    }
}
