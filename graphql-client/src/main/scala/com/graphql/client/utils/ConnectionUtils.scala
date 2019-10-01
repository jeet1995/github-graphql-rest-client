package com.graphql.client.utils


import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClientBuilder

object ConnectionUtils {

  val BASE_GITHUB_API_V4 = "https://api.github.com/graphql"

  val httpClient = HttpClientBuilder.create.build

  val httpUriRequest = new HttpPost(BASE_GITHUB_API_V4)

  httpUriRequest.addHeader(ApplicationConstants.authorizationString, ApplicationConstants.bearerString + " " + ConfigUtils.applicationConfig.getString("githubPersonalAccessToken"))
  httpUriRequest.addHeader(ApplicationConstants.acceptString, ApplicationConstants.applicationSlashJsonString)

}
