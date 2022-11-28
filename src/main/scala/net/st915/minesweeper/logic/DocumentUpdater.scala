package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.component.{Button, Cell, IconContainer}
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.{Constants, Difficulty, GameContext, Util}
import org.scalajs.dom.*

case class DocumentUpdater(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  def updateFlagPlaceButtonText(implicit context: GameContext): IO[Unit] =
    Button.updateText(
      Constants.FlagPlaceButtonId,
      if (context.flagPlaceMode)
        Constants.InFlagPlaceModeText
      else
        Constants.NotFlagPlaceModeText
    )

  def updateCellClasses(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        Cell.updateCellClassName(
          coord,
          if (context.isOpened(coord))
            Constants.OpenedCellClasses
          else
            Constants.NotOpenedCellClasses
        )
    )

  def updateFlagDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        IconContainer.updateVisibility(
          s"flagContainer_${coord.x}_${coord.y}",
          context.isFlagged(coord)
        )
    )

  def updateFlagPlaceholderDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoords(
      difficulty,
      coord =>
        IconContainer.updateVisibility(
          s"flagPlaceholderContainer_${coord.x}_${coord.y}",
          if (context.flagPlaceMode)
            !context.isOpened(coord) && !context.isFlagged(coord)
          else
            false
        )
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
