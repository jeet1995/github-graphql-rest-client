package com.graphql.client

import com.graphql.client.facade.GithubGraphQLClient
import com.graphql.client.models.Data
import com.graphql.client.utils.QueryUtils
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer

/**
  * The driver program to execute the GraphQL client application.
  * */

object GithubGraphQLRunner extends LazyLogging {

  def main(args: Array[String]): Unit = {

    val githubGraphQLClient = new GithubGraphQLClient
    val queries = QueryUtils.queries // Load queries to be run from the conf
    val dataFetched = new ListBuffer[Data] // List of data to be fetched

    queries.foreach {
      query => dataFetched += githubGraphQLClient.executeQuery(query)
    }

    githubGraphQLClient.printData(dataFetched) // Print data
  }
}
