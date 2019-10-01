package com.graphql.client.executor

trait Executor[T, O] {
  def execute(tasks: T): O
}
