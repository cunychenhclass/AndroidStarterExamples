package edu.cuny.bc.cisc3171.githubapp;

import androidx.appcompat.app.AppCompatActivity;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



import android.os.Bundle;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private String TAG="GITHUB_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRepoList("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");
    }

    private String[] getRepoList(String ghToken)
    {
        if (ghToken == null || ghToken.isEmpty()) {
            throw new IllegalArgumentException("ghToken must not be or empty");
        }

        HttpsURLConnection urlConnection;
        try {
            URL githubEndpoint = new URL("https://api.github.com/user/repos");
            HttpsURLConnection conn = (HttpsURLConnection) githubEndpoint.openConnection();
            conn.setRequestProperty("Authorization",  "token" + ghToken);

            if (conn.getResponseCode() == 200) {
                // Success
                // Further processing here
                Snackbar.make(findViewById(R.id.main_activity_layout), R.string.response_ok, Snackbar.LENGTH_LONG).show();
            } else {
                // Error handling code goes here
                Log.e(TAG, "response error");
            }
        } catch (MalformedURLException e) {
            Snackbar.make(findViewById(R.id.main_activity_layout), R.string.malformed_url, Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Snackbar.make(findViewById(R.id.main_activity_layout), R.string.connection_failed, Snackbar.LENGTH_LONG).show();
        }

        return null;
    }
}