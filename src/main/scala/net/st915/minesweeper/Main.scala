package net.st915.minesweeper

import cats.effect.IO
import net.st915.minesweeper.renderer
import org.scalajs.dom.*

@main def main(): Unit = {

  import cats.effect.unsafe.implicits.global
  import cats.syntax.traverse.*

  implicit val _document: HTMLDocument = document
  implicit val _window: Window = window

  val render = renderer.Service.wired[IO]

  val program = for {
    _ <- render
  } yield ()

  program.start.unsafeRunAndForget()

}
