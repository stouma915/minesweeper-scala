package net.st915.minesweeper.idfactory

import net.st915.immutablescalajs.dom.properties.ID
import net.st915.minesweeper.Coordinate

object CanCreateID {

  def apply[F[_], A](using ev: CanCreateID[F, A]): CanCreateID[F, A] = ev

}

trait CanCreateID[F[_], A] {

  def apply(coord: Coordinate): F[ID]

}
