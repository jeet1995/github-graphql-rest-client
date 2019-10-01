package com.graphql.client.executor

import com.graphql.client.models.{Data, Repository, Viewer}
import com.typesafe.scalalogging.LazyLogging

class DataPrintExecutor extends Executor[Data, Data] with LazyLogging {

  override def execute(data: Data): Data = {

    data match {
      case viewer: Viewer => {
        logger.info("Viewer object fetched with name : " + viewer.getName)
        logger.info("Viewer object fetched with name : " + viewer.getLogin)
        viewer
      }
      case repository: Repository => {
        logger.info("Repository object fetched with owner : " + repository.getOwner)
        logger.info("Repository object fetched with fork count : " + repository.getForkCount)
        repository
      }
    }
  }
}
