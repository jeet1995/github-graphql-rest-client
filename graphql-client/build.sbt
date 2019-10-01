name := "graphql-client"

version := "0.1"

scalaVersion := "2.12.0"

sbtVersion := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5",
  "org.json" % "json" % "20180813",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.10",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.0.pr3",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalamock" %% "scalamock" % "4.0.0" % "test"

)

