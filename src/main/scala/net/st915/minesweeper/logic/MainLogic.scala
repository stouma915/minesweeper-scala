package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameContext
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.*

case class MainLogic(gameLogic: GameLogic)(implicit
  doc: Document,
  wind: Window,
  runtime: IORuntime
) {

  def startGameLoop: IO[Unit] = for {
    context <- IO(GameContext.empty)
    _ <- contextLoop(context)
  } yield ()

  def contextLoop(context: GameContext): IO[GameContext] =
    if (context.gameEnded) IO(context)
    else for {
      newContext <- updateContext(context)
      loopContext <- contextLoop(newContext)
    } yield loopContext

  def updateContext(context: GameContext): IO[GameContext] = for {
    maybeEvent <- EventQueue.nextEvent
    newContext <- maybeEvent match {
      case Some(event) =>
        implicit val _context: GameContext = context

        event match {
          case e: CellClickEvent => gameLogic.cellClicked(e)
          case e: CellRightClickEvent => gameLogic.cellRightClicked(e)
          case _ => IO(context)
        }
      case None => IO(context)
    }
    _ <- gameLogic.updateDocument(newContext)
    _ <- EventQueue.increment
  } yield newContext

}
