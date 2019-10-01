package com.graphql.client

import com.graphql.client.facade.GithubGraphQLClient
import com.graphql.client.models.Data
import com.graphql.client.utils.QueryUtils
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

object GithubGraphQLRunner extends LazyLogging {

  def main(args: Array[String]): Unit = {

    val githubGraphQLClient = new GithubGraphQLClient
    val queries = QueryUtils.queries
    val dataFetched = new ListBuffer[Data]

    queries.foreach {
      query => dataFetched += githubGraphQLClient.executeQuery(query)
    }

    githubGraphQLClient.printData(dataFetched)
  }
}
