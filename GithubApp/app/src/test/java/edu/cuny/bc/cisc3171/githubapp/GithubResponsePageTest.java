package edu.cuny.bc.cisc3171.githubapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import edu.cuny.bc.cisc3171.githubapp.Github.GithubClient;
import edu.cuny.bc.cisc3171.githubapp.Github.GithubResponsePage;

@RunWith(RobolectricTestRunner.class)
public class GithubResponsePageTest {
    @Test
    public void test_GithubClient_getRepoList() throws IOException {
        GithubResponsePage page = new GithubResponsePage("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");
        Assert.assertNotNull("getting null", page.getPageRepoList(page.getRepoList1stPageUrl()));
    }
}