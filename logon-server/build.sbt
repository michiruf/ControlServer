//import better.files.File

name := """logon-server"""

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"


// Dependency injection
libraryDependencies += guice

routesGenerator := InjectedRoutesGenerator


// Database driver (rest is applied by PlayEbean plugin)
libraryDependencies += "com.h2database" % "h2" % "1.4.192"

// Captcha for registration
// TODO Maybe take this?
//libraryDependencies += "com.nappin" %% "play-recaptcha" % "2.3"


// Testing libraries for dealing with CompletionStage and websockets
libraryDependencies ++= Seq(
  "org.assertj" % "assertj-core" % "3.6.2" % Test,
  "org.awaitility" % "awaitility" % "2.0.0" % Test,
  "io.vertx" % "vertx-unit" % "3.5.1" % Test
)


// Get common dependency by running the gradle command
lazy val buildCommonGradleDependency = taskKey[Unit]("Execute gradle build for 'common' module")

buildCommonGradleDependency := {
  // Because every damn getParent function did not work in this dumb build script...
  val controlServerRootDirectory = root.base.absolutePath.replaceAll("logon-server[/\\\\]?\\.?", "")
  val controlServerRootDirectoryFile = file(controlServerRootDirectory)

  // Choose the correct build command
  val isWindows = sys.props("os.name").contains("Windows")
  val buildCmd = (if (isWindows) "cmd /c gradlew.bat" else "bash -c gradlew") + " :common:playframeworkSbtDependencyJar"

  // Build the gradle stuff
  if (Process(buildCmd, cwd = controlServerRootDirectoryFile).! != 0) {
    throw new IllegalStateException("Module 'common' build failed!")
  }

  // Move the library to the destination in play (seems like files are automatically closed)
  val sourcePath = controlServerRootDirectory + "common/build/libs/common-playframeworkSbtDependency.jar"
  val sourceFile = file(sourcePath)
  val destinationFile = file("lib/common-playframeworkSbtDependency.jar")
  IO.copyFile(sourceFile, destinationFile)
}

// Add this task as a dependency for other tasks
(compile in Compile) := (compile in Compile).dependsOn(buildCommonGradleDependency).value

(compileIncremental in Compile) := (compileIncremental in Compile).dependsOn(buildCommonGradleDependency).value
