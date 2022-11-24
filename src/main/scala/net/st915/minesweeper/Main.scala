package net.st915.minesweeper

import cats.data.OptionT
import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.component.*
import net.st915.minesweeper.difficulty.{Difficulties, Difficulty}
import net.st915.minesweeper.logic.*
import org.scalajs.dom.*

@main def main(): Unit = {

  implicit val _document: Document = document
  implicit val _window: Window = window
  implicit val _runtime: IORuntime =
    cats.effect.unsafe.implicits.global

  val appendToBody: Element => IO[Unit] = e => IO(document.body.appendChild(e))

  val getDifficulty: IO[Option[Difficulty]] = IO {
    val params = new URLSearchParams(window.location.search)
    if (params.has("d")) Difficulties.All.find(_.id eq params.get("d"))
    else Some(Difficulties.Default)
  }

  import scala.language.implicitConversions
  implicit def ioAToIOOptA[A](io: IO[A]): IO[Option[A]] = io.map(x => Some(x))
  def optT[A](io: IO[Option[A]]): OptionT[IO, A] = OptionT(io)

  val renderGame = for {
    unknownDiff <- optT(UnknownDifficulty.make)
    _ <- optT(appendToBody(unknownDiff))
    difficulty <- optT(getDifficulty)
    gameScreen <- optT(GameScreen.make(difficulty))
    diffSelect <- optT(DifficultySelector.make)
    _ <- optT(IO(Some(unknownDiff.remove())))
    _ <- optT(appendToBody(gameScreen))
    _ <- optT(appendToBody(diffSelect))
    gameLogic <- optT(IO(Some(GameLogic(difficulty))))
    _ <- optT(MainLogic(gameLogic).startGameLoop)
  } yield ()

  val program = for {
    _ <- renderGame.value
    aboutPage <- AboutPage.make
    _ <- appendToBody(aboutPage)
  } yield ()

  program.unsafeRunAndForget()

}
