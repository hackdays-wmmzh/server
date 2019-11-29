package com.wmmzh.backend.imagga;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImaggaClientImpl implements ImaggaClient {
    private final String DEVELOPER_KEY = "df9a097f4a5c96908b0c49ea3e48d1d1";
    private String API_KEY = "acc_926234adbb1966e";;
    private final String KEY_SEPARATOR = ":";

    @Override
    public List<String> getImageInfo(String base64Image) {
        String credentialsToEncode = API_KEY + KEY_SEPARATOR + DEVELOPER_KEY;
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

        HttpResponse<JsonNode> response = Unirest.post("https://api.imagga.com/v2/tags")
                .header("Authorization", "Basic " + basicAuth)
                .header("accept", "application/json")
                .field("image_base64", base64Image)
                .field("language", "de")
                .asJson();

        if (!response.isSuccess()) {
            return Collections.singletonList(response.toString());
        }

        Gson gson = new Gson();
        ImaggaModel orcModel =  gson.fromJson(response.getBody().toString(), ImaggaModel.class);

        return orcModel.getResult().getTags().stream()
                .sorted(Comparator.comparing(ImaggaModel.Tag::getConfidence))
                .limit(5)
                .map(t -> t.getTag().getDe())
                .collect(Collectors.toList());
    }
}
