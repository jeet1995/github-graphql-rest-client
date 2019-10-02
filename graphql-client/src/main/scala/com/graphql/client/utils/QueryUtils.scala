package com.graphql.client.utils

import scala.collection.JavaConverters._

/**
  * Utilities object to load GraphQL queries to be run from queries.conf
  * */

object QueryUtils {
  val queries = ConfigUtils.queriesConfig.getConfigList(ApplicationConstants.queriesString).asScala
}
