package net.st915.minesweeper.util.impl

import cats.effect.Sync
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.util.application.*
import net.st915.minesweeper.{Coordinate, Difficulty, GameState}
import org.scalajs.dom.*

class SyncMineCountCalculator[F[_]: Sync: Get3x3: IfMine: ForAllCoords]
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

  override def calculateAll(difficulty: Difficulty)(implicit
  gameState: GameState): F[List[(Coordinate, Int)]] =
    ForAllCoords[F].perform(difficulty) { coord =>
      calculate(coord, difficulty) >>= { count => Sync[F].pure(coord -> count) }
    }

}
