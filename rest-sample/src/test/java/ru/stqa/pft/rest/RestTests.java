package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Set;

import static java.nio.charset.StandardCharsets.*;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    public String testcaseid;
    public String valueToken;
    public String headername;

    @BeforeMethod
    public void setUp() {
        testcaseid = "H2H-T252";
        valueToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaXJhOjBmYjZhMWQwLWM0MWUtNDI5Yy04Yjk3LWMyMzgwOWRiMDFjOSIsImNvbnRleHQiOnsiYmFzZVVybCI6Imh0dHBzOlwvXC9wZXRyb2xwbHVzLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjVkZDI0MGY1MjNjYmU5MGVlN2I0MDNjNSJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NzIyMzk2OTgsImlhdCI6MTY0MDcwMzY5OH0.DNYeiwwgn96NYan7wIUl_0IYLRVJAHbwAXSKkYbGel4";
        headername = "Authorization";
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1998);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue_qqUber").withDescription("My New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaXJhOjBmYjZhMWQwLWM0MWUtNDI5Yy04Yjk3LWMyMzgwOWRiMDFjOSIsImNvbnRleHQiOnsiYmFzZVVybCI6Imh0dHBzOlwvXC9wZXRyb2xwbHVzLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjVkZDI0MGY1MjNjYmU5MGVlN2I0MDNjNSJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NzIyMzk2OTgsImlhdCI6MTY0MDcwMzY5OH0.DNYeiwwgn96NYan7wIUl_0IYLRVJAHbwAXSKkYbGel4", "");//.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "556699");
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = JsonParser.parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
    @Test
    public void testSteps() throws IOException {
        String json = Request.Get("https://api.zephyrscale.smartbear.com/v2/testcases/" + testcaseid + "/teststeps")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaXJhOjBmYjZhMWQwLWM0MWUtNDI5Yy04Yjk3LWMyMzgwOWRiMDFjOSIsImNvbnRleHQiOnsiYmFzZVVybCI6Imh0dHBzOlwvXC9wZXRyb2xwbHVzLmF0bGFzc2lhbi5uZXQiLCJ1c2VyIjp7ImFjY291bnRJZCI6IjVkZDI0MGY1MjNjYmU5MGVlN2I0MDNjNSJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJleHAiOjE2NzIyMzk2OTgsImlhdCI6MTY0MDcwMzY5OH0.DNYeiwwgn96NYan7wIUl_0IYLRVJAHbwAXSKkYbGel4")
                .execute().returnContent().asString(UTF_8);
        JsonElement parsed = JsonParser.parseString(json).getAsJsonObject();
        System.out.println(parsed);//new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        }
    @Test
    public void testCase() throws IOException {
        String json = Request.Get("https://api.zephyrscale.smartbear.com/v2/testcases/" + testcaseid)
                .addHeader(headername, valueToken)
                .execute().returnContent().asString(UTF_8);
        JsonElement parsed = JsonParser.parseString(json);
        System.out.println(parsed);//new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

}