package com.graphql.client.models

/**
  * This class constitutes the Repository schema of GitHub
  * */

case class Repository(createdAt: String, forkCount: Int, codeOfConduct: CodeOfConduct, commitComments: CommitCommentConnection, owner: RepositoryOwner) extends Data {

  def getCreatedAt = createdAt

  def getForkCount = forkCount

  def getOwner = owner

  def getCodeOfConduct = CodeOfConduct


}

case class CodeOfConduct(body: String) extends Data

case class CommitCommentConnection(nodes: Array[CommitComment]) extends Data

case class CommitComment(author: Actor) extends Data

case class Actor(login: String) extends Data

case class RepositoryOwner(login: String) extends Data