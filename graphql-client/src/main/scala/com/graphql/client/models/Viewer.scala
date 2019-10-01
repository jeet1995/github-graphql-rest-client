package com.graphql.client.models

case class Viewer(name: String, login: String) extends Data {

  def getName = name

  def getLogin = login

}
