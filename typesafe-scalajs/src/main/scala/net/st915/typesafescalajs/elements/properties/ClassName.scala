package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}

object ClassName {

  given classNameEq: Eq[ClassName] with
    override def eqv(x: ClassName, y: ClassName): Boolean =
      x.raw eq y.raw

  given classNameMonoid: Monoid[ClassName] with
    override def combine(x: ClassName, y: ClassName): ClassName =
      ClassName(s"${x.raw} ${y.raw}")

    override def empty: ClassName =
      ClassName("")

}

case class ClassName(raw: String)
