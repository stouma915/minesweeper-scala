package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class BR(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty,
  override val clickEvent: ClickEvent = Monoid[ClickEvent].empty,
  override val rightClickEvent: RightClickEvent = Monoid[RightClickEvent].empty
) extends Element[BR] {

  override def copyWith(newProperty: ClassName): BR =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): BR =
    copy(id = newProperty)

  override def copyWith(newProperty: Childs): BR =
    copy(childs = newProperty)

  override def copyWith(newProperty: ClickEvent): BR =
    copy(clickEvent = newProperty)

  override def copyWith(newProperty: RightClickEvent): BR =
    copy(rightClickEvent = newProperty)

}
