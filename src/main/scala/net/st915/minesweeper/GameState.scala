package net.st915.minesweeper

object GameState {

  def empty: GameState = GameState(
    gameStarted = false,
    stopped = false,
    inFlagPlaceMode = false,
    openedCoords = List(),
    flaggedCoords = List(),
    mines = List()
  )

}

case class GameState(
  gameStarted: Boolean,
  stopped: Boolean,
  inFlagPlaceMode: Boolean,
  openedCoords: List[Coordinate],
  flaggedCoords: List[Coordinate],
  mines: List[Coordinate]
)
