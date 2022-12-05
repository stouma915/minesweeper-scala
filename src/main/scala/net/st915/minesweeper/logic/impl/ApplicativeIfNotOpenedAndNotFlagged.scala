package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfNotOpenedAndNotFlagged[F[_]: Applicative: IfOpened: IfFlagged]
    extends IfNotOpenedAndNotFlagged[F] {

  override def perform[A](coord: Coordinate)(ifTrue: => F[A])(ifFalse: => F[A])(implicit
  gameState: GameState): F[A] =
    IfOpened[F].perform(coord) {
      ifFalse
    } {
      IfFlagged[F].perform(coord) {
        ifFalse
      } {
        ifTrue
      }
    }

}
