package com.graphql.client.observer

/**
  * Trait which defines a generic observer.
  * */

trait Observer[S] {

  // Receive an update on the subject
  def receiveUpdate(subject: S)
}
