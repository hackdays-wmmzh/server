package com.wmmzh.backend.imagga;

import java.io.IOException;
import java.util.List;

public interface ImaggaClient {
    List<String> getImageInfo(String base64Images) throws IOException;
}
