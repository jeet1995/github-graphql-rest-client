package com.graphql.client.models


/**
  * This class constitutes the Viewer schema of GitHub
  * */

case class Viewer(name: String, login: String) extends Data {

  def getName = name

  def getLogin = login

}
