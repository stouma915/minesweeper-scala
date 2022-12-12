package net.st915.minesweeper

object GameState {

  def empty: GameState = GameState()

}

case class GameState(
  gameStarted: Boolean = false,
  stopped: Boolean = false,
  inFlagPlaceMode: Boolean = false,
  openedCoords: List[Coordinate] = List(),
  flaggedCoords: List[Coordinate] = List(),
  mines: List[Coordinate] = List(),
  mineCounts: Map[Coordinate, Int] = Map()
)
