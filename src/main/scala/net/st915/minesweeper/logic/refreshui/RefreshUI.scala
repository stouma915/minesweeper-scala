package net.st915.minesweeper.logic.refreshui

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.refreshui.task.*
import org.scalajs.dom.*

object RefreshUI {

  def wired[F[_]: Sync](gameState: GameState)(implicit document: HTMLDocument): F[Unit] =
    RefreshUI(gameState)

  def apply[F[_]: Sync](gameState: GameState)(implicit document: HTMLDocument): F[Unit] = {
    implicit val _gameState: GameState = gameState

    UpdateButtonText.wired[F]
  }

}
