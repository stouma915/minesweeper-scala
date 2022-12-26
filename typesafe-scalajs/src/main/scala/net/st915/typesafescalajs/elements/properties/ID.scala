package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}

object ID {

  given idEq: Eq[ID] with
    override def eqv(x: ID, y: ID): Boolean =
      x.raw eq y.raw

  given idMonoid: Monoid[ID] with
    override def combine(x: ID, y: ID): ID =
      ID(x.raw + y.raw)

    override def empty: ID =
      ID("")

}

case class ID(raw: String)
