package net.st915.minesweeper.util.typeclasses

import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.ID

object CanCreateIconContainerID {

  def apply[F[_], A](using ev: CanCreateIconContainerID[F, A]): CanCreateIconContainerID[F, A] = ev

}

trait CanCreateIconContainerID[F[_], A] {

  def create(coord: Coordinate): F[ID]

}
