package net.st915.minesweeper

object GameState {

  def empty: GameState = GameState(
    gameStarted = true, // TODO: should be 'false'
    stopped = false,
    inFlagPlaceMode = false,
    openedCoords = List(),
    flaggedCoords = List()
  )

}

case class GameState(
  gameStarted: Boolean,
  stopped: Boolean,
  inFlagPlaceMode: Boolean,
  openedCoords: List[Coordinate],
  flaggedCoords: List[Coordinate]
)
