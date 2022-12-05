package net.st915.minesweeper.logic.refreshui.task

import cats.effect.Sync
import net.st915.minesweeper.Consts.{ElementID, Text}
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*
import net.st915.minesweeper.logic.refreshui.application.*
import net.st915.minesweeper.logic.refreshui.impl.*
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import org.scalajs.dom.*

object UpdateButtonText {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](implicit document: HTMLDocument, gameState: GameState): F[Unit] = {
    implicit val _ifInFlagPlaceMode: IfInFlagPlaceMode[F] = ApplicativeIfInFlagPlaceMode[F]

    implicit val _getElement: GetElement[F] = SyncGetElement[F]
    implicit val _updateTextContent: UpdateTextContent[F] = SyncUpdateTextContent[F]
    implicit val _updateTextContentWithID: UpdateTextContentWithID[F] =
      SyncUpdateTextContentWithID[F]
    implicit val _updateFlagPlaceModeButtonText: UpdateFlagPlaceModeButtonText[F] =
      SyncUpdateFlagPlaceModeButtonText[F]

    UpdateButtonText()
  }

  def apply[
    F[_]: Sync: IfInFlagPlaceMode: UpdateFlagPlaceModeButtonText
  ]()(implicit document: HTMLDocument, gameState: GameState): F[Unit] =
    IfInFlagPlaceMode[F].perform {
      Sync[F].pure(Text.ExitFlagPlaceMode)
    } {
      Sync[F].pure(Text.EnterFlagPlaceMode)
    } >>= UpdateFlagPlaceModeButtonText[F].update

}
