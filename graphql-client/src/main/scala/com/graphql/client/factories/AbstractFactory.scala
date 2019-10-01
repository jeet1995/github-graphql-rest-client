package com.graphql.client.factories

trait AbstractFactory[T, O] {
  def createInstance(instanceType: O): T
}
