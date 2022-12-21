ThisBuild / version := "1.0.0"
ThisBuild / description := "Minesweeper made with Functional Scala.js"
ThisBuild / scalaVersion := "3.2.1"

ThisBuild / semanticdbEnabled := true
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

ThisBuild / scalacOptions ++= Seq(
  "-Werror",
  "-deprecation",
  "-feature",
  "-unchecked"
)

lazy val immutable_scalajs =
  project
    .in(file("immutable-scalajs"))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name := "immutable-scalajs",
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "cats-effect" % "3.4.1",
        "org.scala-js" %%% "scalajs-dom" % "2.1.0"
      )
    )

lazy val minesweeper = project
  .in(file("minesweeper"))
  .dependsOn(immutable_scalajs)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "minesweeper"
  )

lazy val bootstrap = project
  .in(file("bootstrap"))
  .dependsOn(minesweeper)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "minesweeper-bootstrap",
    scalaJSUseMainModuleInitializer := true
  )
