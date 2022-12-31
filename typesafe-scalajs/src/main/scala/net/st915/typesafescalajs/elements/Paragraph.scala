package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class Paragraph(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val children: Children = Monoid[Children].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty
) extends Element[Paragraph] {

  override def copyWith(newProperty: ClassName): Paragraph =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): Paragraph =
    copy(id = newProperty)

  override def copyWith(newProperty: Children): Paragraph =
    copy(children = newProperty)

  override def copyWith(newProperty: ClickEvent): Paragraph =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): Paragraph =
    copy(rightClickEvent = newProperty)

}
