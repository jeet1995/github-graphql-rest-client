package com.graphql.client.facade

import com.graphql.client.executor.DataPrintExecutor
import com.graphql.client.models.Data
import com.graphql.client.observer.{ObservedQueryExecutor, QueryLogger}
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

class GithubGraphQLClient extends LazyLogging {

  val queryLogger = new QueryLogger
  val dataPrintExecutor = new DataPrintExecutor

  def executeQuery(query: Config) = {
    val queryExecutor = new ObservedQueryExecutor(query.getString("description"))
    queryExecutor.addObserver(queryLogger)
    queryExecutor.execute(query)
  }

  def printData(dataFetched: ListBuffer[Data]) = {
    dataFetched.foreach {
      data => dataPrintExecutor.execute(data)
    }
  }

}
