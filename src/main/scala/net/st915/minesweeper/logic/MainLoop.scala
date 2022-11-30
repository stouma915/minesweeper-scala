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
        5
      )
    }
  } yield ()

  def eventLoop(context: GameContext): IO[Unit] =
    for {
      maybeEvent <- EventQueue.nextEvent
      _ <- maybeEvent match {
        case Some(event) =>
          for {
            _ <- {
              implicit val _context: GameContext = context

              event match {
                case e: ButtonClickEvent    => gameLogic.buttonClicked(e)
                case e: CellClickEvent      => gameLogic.cellClicked(e)
                case e: CellRightClickEvent => gameLogic.cellRightClicked(e)
                case _                      => IO.unit
              }
            }
            _ <- docUpdater.updateDocument(context)
          } yield ()
        case None => IO.unit
      }
    } yield ()

}
