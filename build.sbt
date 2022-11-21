ThisBuild / version := "1.0.0"
ThisBuild / description := "Minesweeper made with Scala.js"
ThisBuild / scalaVersion := "3.2.1"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "minesweeper-scala",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-effect" % "3.4.1",
      "org.scala-js" %%% "scalajs-dom" % "2.1.0"
    )
  )
