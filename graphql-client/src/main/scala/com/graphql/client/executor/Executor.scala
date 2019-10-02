package com.graphql.client.executor

/**
  * This trait defines a generic executor of some task.
  * */

trait Executor[T, O] {
  def execute(task: T): O
}
