package com.graphql.client.executor

import com.graphql.client.factories.{AbstractFactory, DataFactory}
import com.graphql.client.models.Data
import com.graphql.client.utils.ConnectionUtils
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import org.apache.http.entity.StringEntity
import org.json.JSONObject


class QueryExecutor(queryDescription: String) extends Executor[Config, Data] with LazyLogging {

  val httpUriRequest = ConnectionUtils.httpUriRequest
  val httpClient = ConnectionUtils.httpClient
  val dataFactory: AbstractFactory[Data, JSONObject] = new DataFactory

  def getQueryDescription = queryDescription


  override def execute(query: Config): Data = {

    logger.info("Executing query with description : " + queryDescription)

    val jSONObject = new JSONObject().put(query.getString("type"), query.getString("query"))
    val entity = new StringEntity(jSONObject.toString)

    httpUriRequest.setEntity(entity)

    val response = httpClient.execute(httpUriRequest)


    response.getEntity match {
      case null => ???
      case x if x != null => {
        val response = scala.io.Source.fromInputStream(x.getContent).getLines().mkString

        logger.info("Query response obtained")

        httpUriRequest.releaseConnection()
        dataFactory.createInstance(new JSONObject(response))
      }
    }

  }
}