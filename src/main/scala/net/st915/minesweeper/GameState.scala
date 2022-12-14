package net.st915.minesweeper

object GameState {

  def empty: GameState = GameState()

}

case class GameState(
  gameStarted: Boolean = false,
  stopped: Boolean = false,
  inFlagPlaceMode: Boolean = false,
  openedCoords: Set[Coordinate] = Set(),
  flaggedCoords: Set[Coordinate] = Set(),
  mines: Set[Coordinate] = Set()
)
