package net.st915.minesweeper.logic.refreshui.application

import net.st915.minesweeper.GameState
import org.scalajs.dom.*

object RefreshUI {

  def apply[F[_]: RefreshUI]: RefreshUI[F] = implicitly

}

trait RefreshUI[F[_]] {

  def perform(gameState: GameState)(implicit document: HTMLDocument): F[Unit]

}
