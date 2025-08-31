package com.AmanEnterprise.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

//LECTURE - 173
//we are creating a utility method which will read the json data and put the values in the HashMap

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        String jsonFilePath = System.getProperty("user.dir") + "src//test//java//com//AmanEnterprise//Data//PurchaseOrder.json";

        //read json to String
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        //convert the above String into HashMap using Jackson DataBind
        ObjectMapper mapper = new ObjectMapper();

        //since the json that we are using is an array and have 2 indexes, it will create 2 HashMaps
        // and after creating these 2 HashMaps, put it in a List
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        //so now, this "data" is a list of 2 HashMaps {{map1}, {map2}}
        return data;
    }

}
