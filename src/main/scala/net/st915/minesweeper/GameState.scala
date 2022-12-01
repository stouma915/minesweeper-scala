package net.st915.minesweeper

object GameState {

  def empty: GameState = GameState(
    gameStarted = false,
    stopped = false,
    inFlagPlaceMode = false
  )

}

case class GameState(
  gameStarted: Boolean,
  stopped: Boolean,
  inFlagPlaceMode: Boolean
)
