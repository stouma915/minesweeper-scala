package net.st915.minesweeper

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.component.*
import net.st915.minesweeper.logic.*
import org.scalajs.dom.*

@main def main(): Unit = {

  implicit val _document: Document = document
  implicit val _window: Window = window
  implicit val _runtime: IORuntime = cats.effect.unsafe.implicits.global

  val appendToBody: Element => IO[Unit] = e => IO(document.body.appendChild(e))

  val getDifficulty: IO[Difficulty] = IO {
    val params = new URLSearchParams(window.location.search)
    if (params.has("d"))
      Difficulties.All.find(_.id eq params.get("d")) match {
        case Some(d) => d
        case None    => Difficulties.Default
      }
    else
      Difficulties.Default
  }

  val renderGame = for {
    renderingScreen <- RenderingScreen.make
    _ <- appendToBody(renderingScreen)
    difficulty <- getDifficulty
    gameScreen <- GameScreen.make(difficulty)
    diffSelect <- DifficultySelector.make
    _ <- IO(renderingScreen.remove())
    _ <- appendToBody(gameScreen)
    _ <- appendToBody(diffSelect)
  } yield ()

  val startMainLoop = for {
    difficulty <- getDifficulty
    gameLogic <- IO(GameLogic(difficulty))
    docUpdater <- IO(DocumentUpdater(difficulty))
    _ <- MainLoop(gameLogic, docUpdater).startMainLoop
  } yield ()

  val program = for {
    infoText <- InformationText.make
    _ <- appendToBody(infoText)
    _ <- renderGame
    _ <- startMainLoop
    aboutPage <- AboutPage.make
    _ <- appendToBody(aboutPage)
  } yield ()

  program.unsafeRunAndForget()

}
