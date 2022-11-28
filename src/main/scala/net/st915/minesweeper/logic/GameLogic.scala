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

              val flagContainer = doc.getElementByIdWithType[HTMLDivElement](
                s"flagContainer_${coord.x}_${coord.y}"
              )
              if (context.isFlagged(coord)) {
                if (flagContainer.style.display != "block")
                  flagContainer.style.display = "block"
              } else {
                if (flagContainer.style.display != "none")
                  flagContainer.style.display = "none"
              }
            }
        }
    )
  } yield ()

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

  def cellClicked(
      event: CellClickEvent
  )(implicit context: GameContext): IO[Unit] = IO {
    val coord = event.coord

    if (!context.isOpened(coord) && !context.isFlagged(coord)) {
      context.addOpened(coord)
    }
  }

  def cellRightClicked(
      event: CellRightClickEvent
  )(implicit context: GameContext): IO[Unit] = IO {
    val coord = event.coord

    if (!context.isOpened(coord)) {
      if (context.isFlagged(coord)) context.removeFlagged(coord)
      else context.addFlagged(coord)
    }
  }

}
