package com.example.project_1.utils;

import java.io.IOException;

import org.json.*;

import jakarta.servlet.http.*;

public class JsonUtils {
    public static void writeToResponse(HttpServletResponse response, JSONObject object) throws IOException {
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write(object.toString());
    }
}
