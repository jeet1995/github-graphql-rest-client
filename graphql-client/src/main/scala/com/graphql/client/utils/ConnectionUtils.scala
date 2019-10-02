package com.graphql.client.utils


import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClientBuilder

/**
  * Utilities object to load connection related information.
  * */

object ConnectionUtils {

  val BASE_GITHUB_API_V4 = ConfigUtils.applicationConfig.getString(ApplicationConstants.githubBaseUrlString)

  val httpClient = HttpClientBuilder.create.build

  val httpUriRequest = new HttpPost(BASE_GITHUB_API_V4)

  httpUriRequest.addHeader(ApplicationConstants.authorizationString, ApplicationConstants.bearerString + " " + ConfigUtils.applicationConfig.getString("githubPersonalAccessToken"))
  httpUriRequest.addHeader(ApplicationConstants.acceptString, ApplicationConstants.applicationSlashJsonString)

}
