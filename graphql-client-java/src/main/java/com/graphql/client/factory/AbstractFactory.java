package com.graphql.client.factory;

import org.json.JSONObject;

public abstract class AbstractFactory<T> {
    public abstract T createInstance(JSONObject jsonObject);
}
