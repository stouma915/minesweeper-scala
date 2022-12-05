package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Consts.ElementID
import net.st915.minesweeper.logic.refreshui.application.UpdateFlagPlaceModeButtonText
import net.st915.minesweeper.ui.application.UpdateTextContentWithID
import org.scalajs.dom.*

class SyncUpdateFlagPlaceModeButtonText[F[_]: UpdateTextContentWithID]
    extends UpdateFlagPlaceModeButtonText[F] {

  override def update(text: String)(implicit document: HTMLDocument): F[Unit] =
    UpdateTextContentWithID[F].update(ElementID.ToggleFlagModeButtonId, text)

}
