package net.st915.minesweeper.logic.refreshui.task

import cats.effect.Sync
import net.st915.minesweeper.Consts.{ElementID, Text}
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.impl.*
import org.scalajs.dom.*

object UpdateButtonText {

  def wired[F[_]: Sync](implicit document: HTMLDocument, gameState: GameState): F[Unit] = {
    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateTextContent: UpdateTextContent[F] = SyncUpdateTextContent[F]

    UpdateButtonText()
  }

  def apply[F[_]: Sync: UpdateTextContent]()(
    implicit document: HTMLDocument,
    gameState: GameState
  ): F[Unit] =
    UpdateTextContent[F].update(
      ElementID.ToggleFlagModeButtonId,
      if (gameState.inFlagPlaceMode)
        Text.ExitFlagPlaceMode
      else
        Text.EnterFlagPlaceMode
    )

}
