package com.testrigor;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

class Result {

    private static final String API_HOST = "http://20.55.57.43:21001";
    private static final String API_PATH = "/api/v1/people";
    private static final Gson GSON_MAPPER = new Gson();

    /*
     * Gets the API response in string format containing people list, parses it into an object,
     * collects the name of the people for creating a collection of strings with those names
     * and returns it. The collection should not contain duplicated names.
     *
     */
    public static Collection<String> collectUniquePeopleNamesFromApiResponse() throws IOException {
        String apiRespStr = callPeopleApi();

        JSONObject jsonApiResponse = XML.toJSONObject(apiRespStr);

        JSONObject response = jsonApiResponse.getJSONObject("Response");
        JSONObject data = response.getJSONObject("data");

        Response apiResponse = GSON_MAPPER.fromJson(String.valueOf(data), Response.class);

        List<Person> peopleList = apiResponse.getData();

        Set<String> uniqueNamesSet = peopleList.stream()
                .map(Person::getName)
                .collect(Collectors.toSet());

        return new ArrayList<>(uniqueNamesSet);
    }

    /*
     * Calls an API that returns a list of people inside a response
     */
    public static String callPeopleApi() throws IOException {
        URL url = new URL(API_HOST + API_PATH);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Accept", "application/xml");

        try (InputStream is = connection.getInputStream()) {
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
                return bf.lines().collect(Collectors.joining("\n"));
            }
        }
    }
}
