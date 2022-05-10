package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("qqUber", "Base_Java_Barancev")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println("'Comment' " + new RepoCommit.Smart(commit).message() + " 'URL' " + commit);
        }
    }
}