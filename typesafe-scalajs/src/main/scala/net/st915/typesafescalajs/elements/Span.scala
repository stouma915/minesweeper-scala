package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class Span(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val children: Children = Monoid[Children].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty
) extends Element[Span] {

  override def copyWith(newProperty: ClassName): Span =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): Span =
    copy(id = newProperty)

  override def copyWith(newProperty: Children): Span =
    copy(children = newProperty)

  override def copyWith(newProperty: ClickEvent): Span =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): Span =
    copy(rightClickEvent = newProperty)

}
