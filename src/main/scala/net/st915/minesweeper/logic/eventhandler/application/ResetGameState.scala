package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object ResetGameState {

  def apply[F[_]: ResetGameState]: ResetGameState[F] = implicitly

}

trait ResetGameState[F[_]] {

  def perform: F[GameState]

}
