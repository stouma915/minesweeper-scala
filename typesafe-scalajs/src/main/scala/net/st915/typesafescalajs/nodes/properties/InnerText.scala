package net.st915.typesafescalajs.nodes.properties

import cats.{Eq, Monoid}

object InnerText {

  given innerTextEq: Eq[InnerText] with
    override def eqv(x: InnerText, y: InnerText): Boolean =
      x.raw eq y.raw

  given innerTextMonoid: Monoid[InnerText] with
    override def combine(x: InnerText, y: InnerText): InnerText =
      InnerText(x.raw + y.raw)

    override def empty: InnerText =
      InnerText("")

}

case class InnerText(raw: String)
