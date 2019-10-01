package com.graphql.client.utils

import com.typesafe.config.ConfigFactory

object ConfigUtils {
  val applicationConfig = ConfigFactory.load()
  val queriesConfig = ConfigFactory.load(ApplicationConstants.queriesDotConfString)
}
