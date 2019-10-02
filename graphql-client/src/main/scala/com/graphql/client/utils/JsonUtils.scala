package com.graphql.client.utils

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Utilities object to initialize Jackson ObjectMapper to map JSON strings to objects.
  * */

object JsonUtils {

  val mapper = new ObjectMapper()

  mapper.registerModule(DefaultScalaModule)

  // Deserialization will not fail in case json response object does not have all the required data
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
