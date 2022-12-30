package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}

object Link {

  given linkEq: Eq[Link] with
    override def eqv(x: Link, y: Link): Boolean =
      x.raw eq y.raw

  given linkMonoid: Monoid[Link] with
    override def combine(x: Link, y: Link): Link =
      Link(x.raw + y.raw)

    override def empty: Link =
      Link("")

}

case class Link(raw: String)
