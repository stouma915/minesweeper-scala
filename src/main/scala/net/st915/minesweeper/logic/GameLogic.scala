package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.*
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
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

  def flagPlaceButtonClicked(implicit context: GameContext): IO[Unit] =
    if (context.gameStarted && !context.gameEnded)
      IO {
        context.flagPlaceMode = !context.flagPlaceMode
      }
    else
      IO.unit

  def restartButtonClicked(implicit context: GameContext): IO[Unit] =
    for {
      _ <- context.init
    } yield ()

  def startGame(coord: Coordinate)(implicit context: GameContext): IO[Unit] =
    for {
      _ <- context.init
      mines <- MineLogic.generate(coord, difficulty)
      _ <- IO(context.mines = mines)
      _ <- IO(context.gameStarted = true)
      _ <- openCell(coord)
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
    if (!context.gameEnded)
      if (context.gameStarted)
        if (!context.flagPlaceMode)
          openCell(event.coord)
        else
          toggleFlagged(event.coord)
      else
        startGame(event.coord)
    else
      IO.unit

  def cellRightClicked(
      event: CellRightClickEvent
  )(implicit context: GameContext): IO[Unit] =
    if (context.gameStarted && !context.gameEnded)
      if (!context.flagPlaceMode)
        toggleFlagged(event.coord)
      else
        IO.unit
    else
      IO.unit

}
