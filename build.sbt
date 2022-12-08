ThisBuild / version := "1.0.0"
ThisBuild / description := "Minesweeper made with Functional Scala.js + cats-effect"
ThisBuild / scalaVersion := "3.2.1"

ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "minesweeper-scala",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      ("org.akka-js" %%% "akkajsactortyped" % "2.2.6.14").cross(CrossVersion.for3Use2_13),
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
