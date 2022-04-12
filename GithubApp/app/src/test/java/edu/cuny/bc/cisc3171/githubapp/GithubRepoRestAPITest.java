package edu.cuny.bc.cisc3171.githubapp;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import edu.cuny.bc.cisc3171.githubapp.Github.GithubClient;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class GithubRepoRestAPITest {
    @Test
    public void test_GithubClient_getRepoList() throws IOException {
        GithubClient client = new GithubClient("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");
        Assert.assertNotNull("getting null", client.getRepoListAsArray());
    }
}