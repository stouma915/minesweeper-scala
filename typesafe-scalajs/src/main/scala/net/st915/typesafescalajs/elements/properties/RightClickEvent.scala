package net.st915.typesafescalajs.elements.properties

import cats.effect.IO
import cats.{Eq, Monoid}

object RightClickEvent {

  given rightClickEventEq: Eq[RightClickEvent] with
    override def eqv(x: RightClickEvent, y: RightClickEvent): Boolean =
      x.raw eq y.raw

  given rightClickEventMonoid: Monoid[RightClickEvent] with
    override def combine(x: RightClickEvent, y: RightClickEvent): RightClickEvent =
      RightClickEvent(x.raw >> y.raw)

    override def empty: RightClickEvent =
      RightClickEvent(IO.unit)

}

case class RightClickEvent(raw: IO[Unit])
