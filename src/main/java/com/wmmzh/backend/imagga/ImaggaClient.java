package com.wmmzh.backend.imagga;

import java.io.IOException;

public interface ImaggaClient {
    String getImageInfo(String base64Images) throws IOException;
}
