package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreateTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            StringBuilder xml = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                xml.append(line);
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            xstream.allowTypes(new Class[]{GroupData.class});
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml.toString());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupAdd(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        ;
        app.group().create(group);
        assertThat(app.group().count(), equalTo(
                before.size() + 1));
        Groups after = app.db().groups();
        ;
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream()
                        .mapToInt(GroupData::getId)
                        .max()
                        .orElseGet(null)))));
    }

    @Test
    public void testBadGroupAdd() {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        ;
        GroupData group = new GroupData().withName("One'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(
                before.size()));
        Groups after = app.db().groups();
        ;
        assertThat(after, equalTo(
                before));
        verifyGroupListInUI();
    }
}