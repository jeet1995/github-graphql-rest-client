package com.graphql.client.executor

import com.graphql.client.factories.{AbstractFactory, DataFactory}
import com.graphql.client.models.Data
import com.graphql.client.utils.{ApplicationConstants, ConnectionUtils}
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import org.apache.http.entity.StringEntity
import org.json.JSONObject


/**
  * This class defines the actual execution of a GraphQL query and returns an instance of the deserialized query response.
  **/

class QueryExecutor(queryDescription: String) extends Executor[Config, Data] with LazyLogging {

  val httpUriRequest = ConnectionUtils.httpUriRequest
  val httpClient = ConnectionUtils.httpClient
  val dataFactory: AbstractFactory[Data, JSONObject] = new DataFactory

  def getQueryDescription = queryDescription


  /**
    * This method executes some GraphQL query.
    *
    * @param query
    **/

  override def execute(query: Config): Data = {

    logger.info("Executing query with description : " + queryDescription)

    val jSONObject = new JSONObject()

    // Add input variables if any
    jSONObject.put(query.getString(ApplicationConstants.typeString), query.getString(ApplicationConstants.queryString))

    // Add the GraphQL query
    jSONObject.put(query.getString(ApplicationConstants.inputTypeString), new JSONObject(query.getString(ApplicationConstants.inputString)))

    val entity = new StringEntity(jSONObject.toString)

    // Set the entity as a request entity
    httpUriRequest.setEntity(entity)

    // Execute the query
    val response = httpClient.execute(httpUriRequest)


    response.getEntity match {
      case null => ???
      case x if x != null => {
        val response = scala.io.Source.fromInputStream(x.getContent).getLines().mkString

        logger.info("Query response obtained")

        // Release connection as at a time only 2 connection resources can be present in memory
        httpUriRequest.releaseConnection()

        // Create an instance of the data created
        dataFactory.createInstance(new JSONObject(response))
      }
    }

  }
}