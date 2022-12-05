package net.st915.minesweeper.logic.refreshui.task

import cats.effect.Sync
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

object UpdateIconVisibility {

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] = {
    implicit val _idFactory: IDFactory[F] = ApplicativeIDFactory[F]

    implicit val _ifOpened: IfOpened[F] = ApplicativeIfOpened[F]
    implicit val _ifFlagged: IfFlagged[F] = ApplicativeIfFlagged[F]
    implicit val _ifInFlagPlaceMode: IfInFlagPlaceMode[F] = ApplicativeIfInFlagPlaceMode[F]

    implicit val _forAllCoords: ForAllCoords[F] = SyncForAllCoords[F]
    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateElementVisibility: UpdateElementVisibility[F] =
      SyncUpdateElementVisibility[F]
    implicit val _updateElementVisibilityWithID: UpdateElementVisibilityWithID[F] =
      SyncUpdateElementVisibilityWithID[F]
    implicit val _updateIconContainerVisibility: UpdateIconContainerVisibility[F] =
      SyncUpdateIconContainerVisibility[F]
    implicit val _updateFlagIconVisibility: UpdateFlagIconVisibility[F] =
      SyncUpdateFlagIconVisibility[F]
    implicit val _updateFlagPlaceholderIconVisibility: UpdateFlagPlaceholderIconVisibility[F] =
      SyncUpdateFlagPlaceholderIconVisibility[F]
    implicit val _updateMineIconVisibility: UpdateMineIconVisibility[F] =
      SyncUpdateMineIconVisibility[F]
    implicit val _showFlagIcon: ShowFlagIcon[F] = SyncShowFlagIcon[F]
    implicit val _showFlagPlaceholderIcon: ShowFlagPlaceholderIcon[F] =
      SyncShowFlagPlaceholderIcon[F]
    implicit val _showMineIcon: ShowMineIcon[F] = SyncShowMineIcon[F]
    implicit val _hideFlagIcon: HideFlagIcon[F] = SyncHideFlagIcon[F]
    implicit val _hideFlagPlaceholderIcon: HideFlagPlaceholderIcon[F] =
      SyncHideFlagPlaceholderIcon[F]
    implicit val _hideMineIcon: HideMineIcon[F] = SyncHideMineIcon[F]

    UpdateIconVisibility(difficulty)
  }

  def apply[
    F[_]: Sync: ForAllCoords: IfInFlagPlaceMode: IfOpened: IfFlagged: ShowFlagIcon: ShowFlagPlaceholderIcon: ShowMineIcon: HideFlagIcon: HideFlagPlaceholderIcon: HideMineIcon
  ](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[List[Unit]] =
    ForAllCoords[F].perform(difficulty) { coord =>
      IfInFlagPlaceMode[F].perform {
        ShowFlagPlaceholderIcon[F].perform(coord)
      } {
        HideFlagPlaceholderIcon[F].perform(coord)
      }
    }

}
