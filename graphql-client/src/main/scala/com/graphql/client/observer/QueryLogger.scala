package com.graphql.client.observer

import com.graphql.client.executor.QueryExecutor
import com.typesafe.scalalogging.LazyLogging

class QueryLogger extends Observer[QueryExecutor] with LazyLogging {
  override def receiveUpdate(subject: QueryExecutor): Unit = {
    logger.info("OBSERVER : Query " + subject.getQueryDescription + " to be executed")
  }
}
