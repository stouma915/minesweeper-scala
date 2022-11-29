package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameContext
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

case class MainLoop(gameLogic: GameLogic, docUpdater: DocumentUpdater)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def startMainLoop: IO[Unit] = for {
    context <- IO(GameContext.empty)
    _ <- IO {
      wind.setInterval(
        () => eventLoop(context).unsafeRunAndForget(),
        10
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
              case e: ButtonClickEvent    => gameLogic.buttonClicked(e)
              case e: CellClickEvent      => gameLogic.cellClicked(e)
              case e: CellRightClickEvent => gameLogic.cellRightClicked(e)
              case _                      => IO.unit
            }
          case None => IO.unit
        }
        _ <- docUpdater.updateDocument(context)
      } yield ()
    } else IO.unit

}
