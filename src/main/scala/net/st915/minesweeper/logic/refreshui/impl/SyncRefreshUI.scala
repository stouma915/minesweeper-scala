package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.refreshui.application.RefreshUI
import net.st915.minesweeper.logic.refreshui.task.application.*
import org.scalajs.dom.*

class SyncRefreshUI[F[_]: Sync: UpdateButtonText] extends RefreshUI[F] {

  override def perform(gameState: GameState)(implicit document: HTMLDocument): F[Unit] = {
    implicit val _gameState: GameState = gameState

    UpdateButtonText[F].update
  }

}
