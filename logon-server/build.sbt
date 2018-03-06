name := """logon-server"""

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"


// Dependency injection
libraryDependencies += guice

routesGenerator := InjectedRoutesGenerator


// Database driver (rest is applied by PlayEbean plugin)
libraryDependencies += "com.h2database" % "h2" % "1.4.192"


// Testing libraries for dealing with CompletionStage...
//libraryDependencies ++= Seq(
//  "org.assertj" % "assertj-core" % "3.6.2" % Test,
//  "org.awaitility" % "awaitility" % "2.0.0" % Test
//)
