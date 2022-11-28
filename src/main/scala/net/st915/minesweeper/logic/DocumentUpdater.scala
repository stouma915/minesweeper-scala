package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.difficulty.Difficulty
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, GameContext, Util}
import org.scalajs.dom.*

case class DocumentUpdater(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def updateFlagPlaceButtonText(implicit context: GameContext): IO[Unit] =
    IO {
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

  def updateCellClasses(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        IO {
          val cellElem =
            doc.getElementByIdWithType[HTMLElement](s"${coord.x}_${coord.y}")
          val clsName = cellElem.className

          if (context.isOpened(coord)) {
            if (clsName != Constants.OpenedCellClasses)
              cellElem.className = Constants.OpenedCellClasses
          } else {
            if (clsName != Constants.NotOpenedCellClasses)
              cellElem.className = Constants.NotOpenedCellClasses
          }
        }
    )

  def updateFlagDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        IO {
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
    )

  def updateFlagPlaceholderDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        IO {
          val flagPlaceholderContainer =
            doc.getElementByIdWithType[HTMLDivElement](
              s"flagPlaceholderContainer_${coord.x}_${coord.y}"
            )

          if (context.flagPlaceMode) {
            if (!context.isOpened(coord) && !context.isFlagged(coord)) {
              if (flagPlaceholderContainer.style.display != "block")
                flagPlaceholderContainer.style.display = "block"
            } else {
              if (flagPlaceholderContainer.style.display != "none")
                flagPlaceholderContainer.style.display = "none"
            }
          } else {
            if (flagPlaceholderContainer.style.display != "none")
              flagPlaceholderContainer.style.display = "none"
          }
        }
    )

  def updateDocument(context: GameContext): IO[Unit] = {
    implicit val _context: GameContext = context

    for {
      _ <- updateFlagPlaceButtonText
      _ <- updateCellClasses
      _ <- updateFlagDisplay
      _ <- updateFlagPlaceholderDisplay
    } yield ()
  }

}
