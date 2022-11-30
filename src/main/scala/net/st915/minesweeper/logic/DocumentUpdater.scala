package net.st915.minesweeper.logic

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.component.*
import net.st915.minesweeper.implicits.*
import net.st915.minesweeper.logic.MineLogic
import net.st915.minesweeper.{Constants, Difficulty, GameContext, Util}
import org.scalajs.dom.*

case class DocumentUpdater(difficulty: Difficulty)(implicit
    doc: Document,
    wind: Window,
    runtime: IORuntime
) {

  import cats.syntax.parallel.*

  def updateFlagPlaceButtonText(implicit context: GameContext): IO[Unit] =
    Button.updateText(
      Constants.FlagPlaceButtonId,
      if (context.flagPlaceMode)
        Constants.InFlagPlaceModeText
      else
        Constants.NotFlagPlaceModeText
    )

  def updateCellClasses(implicit context: GameContext): IO[Unit] =
    Util.forAllCoordsPar(
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
    Util.forAllCoordsPar(
      difficulty,
      coord =>
        IconContainer.updateVisibility(
          s"flagContainer_${coord.x}_${coord.y}",
          context.isFlagged(coord)
        )
    )

  def updateFlagPlaceholderDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoordsPar(
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

  def updateMineDisplay(implicit context: GameContext): IO[Unit] =
    Util.forAllCoordsPar(
      difficulty,
      coord =>
        IconContainer.updateVisibility(
          s"mineContainer_${coord.x}_${coord.y}",
          context.isOpened(coord) && context.isMine(coord)
        )
    )

  def updateMineCount(implicit context: GameContext): IO[Unit] =
    Util.forAllCoordsPar(
      difficulty,
      coord =>
        {
          val mineCount = MineLogic.calcMineCount(
            context,
            coord,
            difficulty
          )

          val cond =
            context.isOpened(coord) && !context.isMine(coord)

          (1 to 8)
            .map { i =>
              MineCountContainer
                .updateVisibility(
                  s"mineCount_${i}_${coord.x}_${coord.y}",
                  cond && (i eq mineCount)
                )
            }
            .toList
        }.parSequence
    )

  def updateDocument(context: GameContext): IO[Unit] = {
    implicit val _context: GameContext = context

    val programs = List(
      updateFlagPlaceButtonText,
      updateCellClasses,
      updateFlagDisplay,
      updateFlagPlaceholderDisplay,
      updateMineDisplay,
      updateMineCount
    )

    for {
      _ <- programs.parSequence
    } yield ()
  }

}
