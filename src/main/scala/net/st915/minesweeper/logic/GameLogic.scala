package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.event.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Coordinate, GameContext, Util}
import org.scalajs.dom.{Document, HTMLElement, Window, console}

import scala.util.chaining.*

case class GameLogic(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def updateDocument(context: GameContext): IO[Unit] = for {
    _ <- IO {
      val element = doc.getElementById(Constants.FlagPlaceButtonId)
      val currentText = element.textContent

      if (context.flagPlaceMode) {
        if (currentText != Constants.InFlagPlaceModeText)
          element.textContent = Constants.InFlagPlaceModeText
      } else {
        if (currentText != Constants.NotFlagPlaceModeText)
          element.textContent = Constants.NotFlagPlaceModeText
      }
    }
    _ <- Util.forAllCoords(
      difficulty,
      coord =>
        IO {
          doc
            .getElementByIdWithType[HTMLElement](s"${coord.x}_${coord.y}")
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
    )
  } yield ()

  def flagPlaceButtonClicked(implicit context: GameContext): IO[Unit] = IO {
    context.flagPlaceMode = !context.flagPlaceMode
  }

  def restartButtonClicked(implicit context: GameContext): IO[Unit] =
    for {
      _ <- context.init
    } yield ()

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
