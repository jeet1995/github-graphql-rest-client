package com.graphql.client.factory;

import com.google.gson.Gson;
import com.graphql.client.model.Data;
import com.graphql.client.model.Repository;
import com.graphql.client.model.Viewer;
import org.json.JSONObject;

public class GraphQLModelFactory extends AbstractFactory<Data> {
    @Override
    public Data createInstance(JSONObject jsonObject) {

        String instanceType = jsonObject.getJSONObject("data").keySet().toArray()[0].toString();
        String response = jsonObject.getJSONObject("data").get(instanceType).toString();

        switch (instanceType) {
        case "viewer":
            return new Gson().fromJson(response, Viewer.class);
        case "repository":
            return new Gson().fromJson(response, Repository.class);

        }
        return null;
    }
}
