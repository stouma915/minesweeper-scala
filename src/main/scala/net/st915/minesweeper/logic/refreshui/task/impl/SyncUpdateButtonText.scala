package net.st915.minesweeper.logic.refreshui.task.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.{ElementID, Text}
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.task.application.UpdateButtonText
import org.scalajs.dom.*

class SyncUpdateButtonText[F[_]: Sync: UpdateTextContent] extends UpdateButtonText[F] {

  override def update(implicit document: HTMLDocument, gameState: GameState): F[Unit] =
    UpdateTextContent[F].update(
      ElementID.ToggleFlagModeButtonId,
      if (gameState.inFlagPlaceMode)
        Text.ExitFlagPlaceMode
      else
        Text.EnterFlagPlaceMode
    )

}
