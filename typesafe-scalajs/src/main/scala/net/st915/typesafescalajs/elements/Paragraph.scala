package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.elements.properties.*

final case class Paragraph(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends CopyableElement[Paragraph] {

  override def copyWith(newProperty: ClassName): Paragraph =
    copy(className = newProperty)

  override def copyWith(newProperty: ID): Paragraph =
    copy(id = newProperty)

  override def copyWith(newProperty: Childs): Paragraph =
    copy(childs = newProperty)

}
