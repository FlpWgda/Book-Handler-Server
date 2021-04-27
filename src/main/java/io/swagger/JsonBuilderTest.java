package io.swagger;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class JsonBuilderTest {
    public static void main(String[] args){

        List<String> list = new ArrayList<String>();
        list.add("India");
        list.add("Australia");
        list.add("England");
        list.add("South Africa");
        list.add("West Indies");
        list.add("New Zealand");
        // this method converts a list to JSON Array
        JSONArray jsonArray = new JSONArray(list);
        System.out.println(jsonArray);

    }
}
