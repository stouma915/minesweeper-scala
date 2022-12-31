package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class Div(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val children: Children = Monoid[Children].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty
) extends Element[Div] {

  override def copyWith(newProperty: ClassName): Div =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): Div =
    copy(id = newProperty)

  override def copyWith(newProperty: Children): Div =
    copy(children = newProperty)

  override def copyWith(newProperty: ClickEvent): Div =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): Div =
    copy(rightClickEvent = newProperty)

}
