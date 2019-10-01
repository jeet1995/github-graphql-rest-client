package com.graphql.client.observer

trait Observer[S] {
  def receiveUpdate(subject: S)
}
