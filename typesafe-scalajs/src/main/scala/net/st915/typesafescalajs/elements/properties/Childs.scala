package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}
import net.st915.typesafescalajs.Node

object Childs {

  given childsEq: Eq[Childs] with
    override def eqv(x: Childs, y: Childs): Boolean =
      x.raw eq y.raw

  given childsMonoid: Monoid[Childs] with
    override def combine(x: Childs, y: Childs): Childs =
      Childs(x.raw ::: y.raw)

    override def empty: Childs =
      Childs(Nil)

}

case class Childs(raw: List[Node])
