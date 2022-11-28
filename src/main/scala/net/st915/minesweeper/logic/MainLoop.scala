package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameContext
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

case class MainLoop(gameLogic: GameLogic)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def startMainLoop: IO[Unit] = for {
    context <- IO(GameContext.empty)
    _ <- IO {
      wind.setInterval(
        () => eventLoop(context).unsafeRunAndForget(),
        1
      )
    }
  } yield ()

  def eventLoop(context: GameContext): IO[Unit] =
    if (!context.gameEnded) {
      for {
        maybeEvent <- EventQueue.nextEvent
        _ <- maybeEvent match {
          case Some(event) =>
            implicit val _context: GameContext = context

            event match {
              case e: CellClickEvent      => gameLogic.cellClicked(e)
              case e: CellRightClickEvent => gameLogic.cellRightClicked(e)
              case _: FlagPlaceButtonClickEvent =>
                gameLogic.flagPlaceButtonClicked
              case _: RestartButtonClickEvent => gameLogic.restartButtonClicked
              case _                          => IO.unit
            }
          case None => IO.unit
        }
        _ <- gameLogic.updateDocument(context)
      } yield ()
    } else IO.unit

}
