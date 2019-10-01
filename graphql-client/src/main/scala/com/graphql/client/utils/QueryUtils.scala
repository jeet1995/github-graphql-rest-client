package com.graphql.client.utils

import scala.collection.JavaConverters._

object QueryUtils {
  val queries = ConfigUtils.queriesConfig.getConfigList(ApplicationConstants.queriesString).asScala
}
