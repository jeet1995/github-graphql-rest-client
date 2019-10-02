package com.graphql.client.executor

import com.graphql.client.models.{Data, Repository, Viewer}
import com.typesafe.scalalogging.LazyLogging

/**
  * This class executes a task which prints the response data.
  **/

class DataPrintExecutor extends Executor[Data, Data] with LazyLogging {


  /**
    * This method executes the task of printing response data.
    *
    * @param data The data paramater which is to be printed.
    **/

  override def execute(data: Data): Data = {

    // Match the dynamic subtype of Data
    data match {
      case viewer: Viewer => {
        logger.info("Viewer object fetched with name : " + viewer.getName)
        logger.info("Viewer object fetched with viewer login : " + viewer.getLogin)
        viewer
      }
      case repository: Repository => {
        logger.info("Repository object fetched with owner : " + repository.getOwner)
        logger.info("Repository object fetched with fork count : " + repository.getForkCount)
        logger.info("Repository object fetched with owner login : " + repository.getOwner.login)
        logger.info("Repository object fetched with no. of commit comments : " + repository.commitComments.nodes.length)
        repository
      }
    }
  }
}
