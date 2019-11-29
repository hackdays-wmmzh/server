package com.wmmzh.backend.imagga;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Comparator;

@Service
public class ImaggaClientImpl implements ImaggaClient {
    private final String DEVELOPER_KEY = "df9a097f4a5c96908b0c49ea3e48d1d1";
    private String API_KEY = "acc_926234adbb1966e";;
    private final String KEY_SEPARATOR = ":";

    @Value("${rijad.test}")
    private String base64Image;

    @Override
    public String getImageInfo(String base64Images) {
        String credentialsToEncode = API_KEY + KEY_SEPARATOR + DEVELOPER_KEY;
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

        HttpResponse<JsonNode> response = Unirest.post("https://api.imagga.com/v2/tags")
                .header("Authorization", "Basic " + basicAuth)
                .header("accept", "application/json")
                .field("image_base64", base64Image)
                .field("language", "de")
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
