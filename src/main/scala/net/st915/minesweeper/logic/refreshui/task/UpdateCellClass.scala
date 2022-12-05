package net.st915.minesweeper.logic.refreshui.task

import cats.effect.Sync
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.impl.*
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import net.st915.minesweeper.util.application.IDFactory
import net.st915.minesweeper.util.impl.ApplicativeIDFactory
import net.st915.minesweeper.{Difficulty, GameState}
import org.scalajs.dom.*

object UpdateCellClass {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] = {
    implicit val _idFactory: IDFactory[F] = ApplicativeIDFactory[F]

    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]

    implicit val _forAllCoords: ForAllCoords[F] = SyncForAllCoords[F]
    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateHTMLClassWithID: UpdateHTMLClassWithID[F] = SyncUpdateHTMLClassWithID[F]
    implicit val _updateCellOpening: UpdateCellOpening[F] = SyncUpdateCellOpening[F]
    implicit val _closeCell: CloseCell[F] = SyncCloseCell[F]
    implicit val _openCell: OpenCell[F] = SyncOpenCell[F]

    UpdateCellClass(difficulty)
  }

  def apply[F[_]: Sync: ForAllCoords: IfOpened: OpenCell: CloseCell](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] =
    ForAllCoords[F].perform(difficulty) { coord =>
      IfOpened[F].perform(coord) {
        OpenCell[F].perform(coord)
      } {
        CloseCell[F].perform(coord)
      }
    }

}
