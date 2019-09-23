package com.graphql.client;

import com.graphql.client.factory.AbstractFactory;
import com.graphql.client.factory.GraphQLModelFactory;
import com.graphql.client.model.Data;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GraphqlRunner {
    public static void main(String[] args) {

        Config config = ConfigFactory.load();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.github.com/graphql");

        httpPost.addHeader("Authorization", "Bearer " + config.getString("github.personal.access.token"));
        httpPost.addHeader("Accept", "application/json");

        List<Data> datas = new ArrayList<>();
        AbstractFactory<Data> graphQLModelFactory = new GraphQLModelFactory();

        List<? extends Config> queryConfigs = ConfigFactory.load("queries.conf").getConfigList("queries");

        queryConfigs.forEach(queryConfig -> {
            try {
                JSONObject jsonRequest = new JSONObject().put(queryConfig.getString("type"), queryConfig.getString
                        ("query"));
                StringEntity entity = new StringEntity(jsonRequest.toString());
                httpPost.setEntity(entity);
                HttpResponse response = httpClient.execute(httpPost);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                datas.add(graphQLModelFactory.createInstance(new JSONObject(stringBuilder.toString())));


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });




    }
}