package com.graphql.client.observer

import com.graphql.client.executor.QueryExecutor
import com.graphql.client.models.Data
import com.typesafe.config.Config


/**
  * Class which executes a query along with being a subject for an observer.
  * */

class ObservedQueryExecutor(queryDescription: String) extends QueryExecutor(queryDescription) with Subject[QueryExecutor] {

  override def execute(query: Config): Data = {
    notifyObservers()
    super.execute(query)
  }
}
