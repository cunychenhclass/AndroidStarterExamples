package edu.cuny.bc.cisc3171.githubapp.Github;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GithubResponsePage {
    private String ghToken;

    public String getRepoList1stPageUrl() {
        String pageUrl = "https://api.github.com/user/repos?page=1";
        return pageUrl;
    }

    public GithubResponsePage(String ghToken) {
        this.ghToken = ghToken;
    }

    public static GithubResponsePage getPage(String pageUrl) {
        return  null;
    }

    public List<String> getRepoList(List<String> repoList) {
        if (ghToken == null || ghToken.isEmpty()) {
            throw new IllegalArgumentException("ghToken must not be or empty");
        }

        String pageUrl = getRepoList1stPageUrl();
        while (true) {
            GithubResponsePage page = GithubResponsePage.getPage(pageUrl);
            page.getRepoList(repoList);
            if (!page.hasNextPage()) {
                break;
            }
            pageUrl = page.getPageUrl();
        }
        return repoList;

    }

    public boolean hasNextPage() {
        return false;
    }

    public String getPageUrl() {
        return null;
    }

    public List<String> getPageRepoList(String pageUrl) throws IOException {
        List<String> repoList = new LinkedList<String>();
        HttpsURLConnection conn = null;
        try {
            URL githubEndpoint = new URL(pageUrl);
            conn = (HttpsURLConnection) githubEndpoint.openConnection();
            conn.setRequestProperty("Authorization",  "token " + ghToken);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Connected");

                repoList = parseResponse(conn);
            } else {
                // Error handling code goes here
                System.out.println("connection failed with " + responseCode);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return repoList;
    }


    private List<String> parseResponse(HttpURLConnection conn) throws IOException {
        List<String> repoList = new LinkedList<String>();

        InputStream responseBody = conn.getInputStream();
        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
        JsonReader jsonReader = null;


        try {
            jsonReader = new JsonReader(responseBodyReader);

            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String key = jsonReader.nextName();
                    if (key.equals("full_name")) {
                        String value = jsonReader.nextString();
                        repoList.add(value);

                        System.out.println(value);
                    } else {
                        jsonReader.skipValue(); // Skip values of other keys
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
        } finally {
            if (jsonReader != null) {
                jsonReader.close();
            }
        }
        return repoList;
    }
}
