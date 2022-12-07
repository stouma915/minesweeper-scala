package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*
import net.st915.minesweeper.util.application.*
import net.st915.minesweeper.util.impl.*
import net.st915.minesweeper.{Coordinate, Difficulty, GameState}

object StartGameLogic {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](startPoint: Coordinate, difficulty: Difficulty)(implicit
  gameState: GameState): F[GameState] = {
    implicit val _ifMine: IfMine[F] = ApplicativeIfMine[F]
    implicit val _forAllCoords: ForAllCoords[F] = SyncForAllCoords[F]

    implicit val _get3x3: Get3x3[F] = ApplicativeGet3x3[F]
    implicit val _mineGenerator: MineGenerator[F] = SyncMineGenerator[F]

    implicit val _updateMines: UpdateMines[F] = ApplicativeUpdateMines[F]
    implicit val _updateGameStartedProperty: UpdateGameStartedProperty[F] =
      ApplicativeUpdateGameStartedProperty[F]

    StartGameLogic(startPoint, difficulty)
  }

  def apply[
    F[_]: Sync: UpdateMines: UpdateGameStartedProperty: MineGenerator
  ](startPoint: Coordinate, difficulty: Difficulty)(implicit gameState: GameState): F[GameState] =
    MineGenerator[F].perform(startPoint, difficulty) >>=
      UpdateMines[F].update

}
