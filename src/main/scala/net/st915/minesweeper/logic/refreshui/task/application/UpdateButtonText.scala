package net.st915.minesweeper.logic.refreshui.task.application

import net.st915.minesweeper.GameState
import org.scalajs.dom.*

object UpdateButtonText {

  def apply[F[_]: UpdateButtonText]: UpdateButtonText[F] = implicitly

}

trait UpdateButtonText[F[_]] {

  def update(implicit document: HTMLDocument, gameState: GameState): F[Unit]

}
