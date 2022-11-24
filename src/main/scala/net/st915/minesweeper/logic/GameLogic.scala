package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, GameContext}
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import org.scalajs.dom.{Document, Window, console}

import scala.util.chaining.*

case class GameLogic(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def updateDocument(context: GameContext): IO[Unit] = IO {}

  def cellClicked(
      event: CellClickEvent
  )(implicit context: GameContext): IO[Unit] = IO {
    val coord = event.coord

    if (!context.isOpened(coord)) {
      context.addOpened(coord)
    }
  }

  def cellRightClicked(
      event: CellRightClickEvent
  )(implicit context: GameContext): IO[Unit] = IO {
    context
  }

}
