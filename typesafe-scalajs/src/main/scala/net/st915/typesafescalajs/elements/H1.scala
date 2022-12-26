package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class H1(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends CopyableElement[H1] {

  override def copyWith(newProperty: ClassName): H1 =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): H1 =
    copy(id = newProperty)

  override def copyWith(newProperty: Childs): H1 =
    copy(childs = newProperty)

}
