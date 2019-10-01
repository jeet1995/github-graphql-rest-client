package com.graphql.client.factories

import com.graphql.client.models.{Data, Repository, Viewer}
import com.graphql.client.utils.JsonUtils
import org.json.JSONObject

class DataFactory extends AbstractFactory[Data, JSONObject] {
  override def createInstance(jsonObject: JSONObject): Data = {
    val instanceType = jsonObject.getJSONObject("data").keySet().iterator().next()
    val instance = jsonObject.getJSONObject("data").getJSONObject(instanceType).toString

    instanceType match {
      case "viewer" => JsonUtils.mapper.readValue(instance, classOf[Viewer])
      case "repository" => JsonUtils.mapper.readValue(instance, classOf[Repository])
    }
  }
}

