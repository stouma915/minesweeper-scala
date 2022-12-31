package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class H1(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val children: Children = Monoid[Children].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty
) extends Element[H1] {

  override def copyWith(newProperty: ClassName): H1 =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): H1 =
    copy(id = newProperty)

  override def copyWith(newProperty: Children): H1 =
    copy(children = newProperty)

  override def copyWith(newProperty: ClickEvent): H1 =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): H1 =
    copy(rightClickEvent = newProperty)

}
