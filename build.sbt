ThisBuild / version := "1.0.0"
ThisBuild / description := "Minesweeper made with Functional Scala.js"
ThisBuild / scalaVersion := "3.2.1"

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

ThisBuild / scalacOptions += "-deprecation"

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

inThisBuild(
  List(
    scalaVersion := "3.2.1",
    semanticdbEnabled := true
  )
)
