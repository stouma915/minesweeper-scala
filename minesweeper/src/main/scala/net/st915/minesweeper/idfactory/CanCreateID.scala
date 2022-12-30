package net.st915.minesweeper.idfactory

import net.st915.minesweeper.Coordinate
import net.st915.typesafescalajs.elements.properties.ID

object CanCreateID {

  def apply[F[_], A](using ev: CanCreateID[F, A]): CanCreateID[F, A] = ev

}

trait CanCreateID[F[_], A] {

  def apply(coord: Coordinate): F[ID]

}
