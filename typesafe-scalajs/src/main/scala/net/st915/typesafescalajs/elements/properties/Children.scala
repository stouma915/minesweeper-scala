package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}
import net.st915.typesafescalajs.Node

object Children {

  given childrenEq: Eq[Children] with
    override def eqv(x: Children, y: Children): Boolean =
      x.raw eq y.raw

  given childrenMonoid: Monoid[Children] with
    override def combine(x: Children, y: Children): Children =
      Children(x.raw ::: y.raw)

    override def empty: Children =
      Children(Nil)

}

case class Children(raw: List[Node])
