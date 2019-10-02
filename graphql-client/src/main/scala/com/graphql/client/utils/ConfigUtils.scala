package com.graphql.client.utils

import com.typesafe.config.ConfigFactory

/**
  * Utilities object to load *.conf content into a Config object.
  * */
object ConfigUtils {
  val applicationConfig = ConfigFactory.load() // Application config loaded from application.conf
  val queriesConfig = ConfigFactory.load(ApplicationConstants.queriesDotConfString) // Queries config loaded from application.conf
}
