package edu.cuny.bc.cisc3171.githubapp.Github;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class GithubClient {
    private String ghToken;

    public GithubClient(String token) {
        ghToken = token;
    }

    public String[] getRepoListAsArray() throws MalformedURLException, IOException {
        return  null;
    }
}
