package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.attributes.HasHyperlink
import net.st915.typesafescalajs.elements.properties.*

final case class Anchor(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val children: Children = Monoid[Children].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty,
  override val href: Link = Monoid[Link].empty
) extends Element[Anchor] with HasHyperlink[Anchor] {

  override def copyWith(newProperty: ClassName): Anchor =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): Anchor =
    copy(id = newProperty)

  override def copyWith(newProperty: Children): Anchor =
    copy(children = newProperty)

  override def copyWith(newProperty: ClickEvent): Anchor =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): Anchor =
    copy(rightClickEvent = newProperty)

  override def copyWith(newProperty: Link): Anchor =
    copy(href = newProperty)

}
