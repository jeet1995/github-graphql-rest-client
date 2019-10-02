package com.graphql.client.observer


/**
  * Trait which defines a subject of generic type.
  * */

trait Subject[S] {
  this: S =>
  private var observers: List[Observer[S]] = Nil

  // Add an Observer of generic type S
  def addObserver(observer: Observer[S]) = observers = observer :: observers

  // Notify observers
  def notifyObservers() = observers.foreach(_.receiveUpdate(this))
}
