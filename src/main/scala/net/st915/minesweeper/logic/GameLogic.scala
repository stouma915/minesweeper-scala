package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate, GameContext, Util}
import org.scalajs.dom.*

import scala.util.chaining.*

case class GameLogic(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def buttonClicked(
      event: ButtonClickEvent
  )(implicit context: GameContext): IO[Unit] =
    event.buttonId match {
      case Constants.FlagPlaceButtonId => flagPlaceButtonClicked
      case Constants.RestartButtonId   => restartButtonClicked
      case _                           => IO.unit
    }

  def flagPlaceButtonClicked(implicit context: GameContext): IO[Unit] = IO {
    context.flagPlaceMode = !context.flagPlaceMode
  }

  def restartButtonClicked(implicit context: GameContext): IO[Unit] =
    for {
      _ <- context.init
    } yield ()

  def openCell(coord: Coordinate)(implicit context: GameContext): IO[Unit] =
    IO {
      if (!context.isOpened(coord) && !context.isFlagged(coord)) {
        context.addOpened(coord)
      }
    }

  def toggleFlagged(
      coord: Coordinate
  )(implicit context: GameContext): IO[Unit] =
    IO {
      if (!context.isOpened(coord)) {
        if (context.isFlagged(coord)) context.removeFlagged(coord)
        else context.addFlagged(coord)
      }
    }

  def cellClicked(
      event: CellClickEvent
  )(implicit context: GameContext): IO[Unit] =
    if (!context.flagPlaceMode)
      openCell(event.coord)
    else
      toggleFlagged(event.coord)

  def cellRightClicked(
      event: CellRightClickEvent
  )(implicit context: GameContext): IO[Unit] =
    if (!context.flagPlaceMode)
      toggleFlagged(event.coord)
    else
      IO.unit

}
