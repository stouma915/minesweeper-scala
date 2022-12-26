package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.elements.typeclasses.CanCopyWithNewProperty

object Paragraph {

  given canCopyParagraphWithNewClassName: CanCopyWithNewProperty[Paragraph, ClassName] with
    override def apply(newProperty: ClassName)(element: Paragraph): Paragraph =
      element.copy(className = newProperty)

  given canCopyParagraphWithNewID: CanCopyWithNewProperty[Paragraph, ID] with
    override def apply(newProperty: ID)(element: Paragraph): Paragraph =
      element.copy(id = newProperty)

  given canCopyParagraphWithNewChilds: CanCopyWithNewProperty[Paragraph, Childs] with
    override def apply(newProperty: Childs)(element: Paragraph): Paragraph =
      element.copy(childs = newProperty)

}

case class Paragraph(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends Element
