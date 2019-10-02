package com.graphql.client.factories

import com.graphql.client.models.{Data, Repository, Viewer}
import com.graphql.client.utils.{ApplicationConstants, JsonUtils}
import org.json.JSONObject


/**
  * This class defines a factory class to create instances of type Data
  * */

class DataFactory extends AbstractFactory[Data, JSONObject] {

  /**
    *
    * @param jsonObject The jsonObject which is to be deserialized. This is a response of the GraphQL query.
    * @return data The data which is deserialized.
    * */

  override def createInstance(jsonObject: JSONObject): Data = {

    // Type of the instance to be created
    val instanceType = jsonObject.getJSONObject(ApplicationConstants.dataString).keySet().iterator().next()

    // The instance itself albeit represented as a string
    val instance = jsonObject.getJSONObject(ApplicationConstants.dataString).getJSONObject(instanceType).toString

    instanceType match {
      case ApplicationConstants.viewerString => JsonUtils.mapper.readValue(instance, classOf[Viewer])
      case ApplicationConstants.repositoryString => JsonUtils.mapper.readValue(instance, classOf[Repository])
    }
  }
}

