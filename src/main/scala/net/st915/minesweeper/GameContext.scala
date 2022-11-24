package net.st915.minesweeper

object GameContext {

  def empty: GameContext = GameContext(
    false,
    false
  )

}

case class GameContext(
  gameStarted: Boolean,
  gameEnded: Boolean
) {

  def updateGameStarted(bool: Boolean): GameContext =
    GameContext(
      bool,
      gameEnded
    )

  def updateGameEnded(bool: Boolean): GameContext =
    GameContext(
      gameStarted,
      bool
    )

}
