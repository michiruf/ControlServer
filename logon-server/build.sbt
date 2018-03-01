name := """logon-server"""

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")


// Dependency injection
libraryDependencies += guice

routesGenerator := InjectedRoutesGenerator


// Database
libraryDependencies += "com.h2database" % "h2" % "1.4.196"


// Testing libraries for dealing with CompletionStage...
libraryDependencies ++= Seq(
  "org.assertj" % "assertj-core" % "3.6.2" % Test,
  "org.awaitility" % "awaitility" % "2.0.0" % Test
)


// Frontend stuff
// NOTE Disabled because did not work immediately
//libraryDependencies ++= Seq(
//  "org.webjars" %% "webjars-play" % "2.6.3",
//  "org.webjars" % "bootstrap" % "4.0.0",
//  "org.webjars" % "jquery" % "3.3.1"
//)
