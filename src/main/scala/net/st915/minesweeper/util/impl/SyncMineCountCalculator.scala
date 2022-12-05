package net.st915.minesweeper.util.impl

import cats.effect.Sync
import net.st915.minesweeper.{Coordinate, Difficulty, GameState}
import net.st915.minesweeper.logic.application.IfMine
import net.st915.minesweeper.util.application.*

class SyncMineCountCalculator[F[_]: Sync: Get3x3: IfMine]
    extends MineCountCalculator[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def calculate(coord: Coordinate, difficulty: Difficulty)(implicit
  gameState: GameState): F[Int] =
    for {
      coords <- Get3x3[F].get(coord, difficulty)
      mines <- coords.map { coord =>
        IfMine[F].perform(coord) {
          Sync[F].pure(true)
        } {
          Sync[F].pure(false)
        }
      }.sequence
    } yield mines.count(x => x)

}
