package net.st915.typesafescalajs.elements.properties

import cats.{Eq, Monoid}
import cats.effect.IO

object ClickEvent {

  given clickEventEq: Eq[ClickEvent] with
    override def eqv(x: ClickEvent, y: ClickEvent): Boolean =
      x.raw eq y.raw

  given clickEventMonoid: Monoid[ClickEvent] with
    override def combine(x: ClickEvent, y: ClickEvent): ClickEvent =
      ClickEvent(x.raw >> y.raw)

    override def empty: ClickEvent =
      ClickEvent(IO.unit)

}

case class ClickEvent(raw: IO[Unit])
