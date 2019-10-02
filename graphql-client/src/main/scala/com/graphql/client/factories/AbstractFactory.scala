package com.graphql.client.factories

/**
  * This trait defines an abstraction of a factory class.
  * */

trait AbstractFactory[T, O] {
  def createInstance(instanceType: O): T
}
