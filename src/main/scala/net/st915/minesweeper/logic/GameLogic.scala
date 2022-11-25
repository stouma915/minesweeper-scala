package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate, GameContext}
import org.scalajs.dom.{Document, HTMLElement, Window, console}

import scala.util.chaining.*

case class GameLogic(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def updateDocument(context: GameContext): IO[Unit] = IO {
    (0 until difficulty.height).foreach { y =>
      (0 until difficulty.width).foreach { x =>
        val coord = Coordinate(x, y)

        doc
          .getElementByIdWithType[HTMLElement](s"${x}_$y")
          .tap { cell =>
            val clsName = cell.className

            if (context.isOpened(coord)) {
              if (clsName != Constants.OpenedCellClasses)
                cell.className = Constants.OpenedCellClasses
            } else {
              if (clsName != Constants.NotOpenedCellClasses)
                cell.className = Constants.NotOpenedCellClasses
            }
          }
      }
    }
  }

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
  )(implicit context: GameContext): IO[Unit] = IO {}

}
