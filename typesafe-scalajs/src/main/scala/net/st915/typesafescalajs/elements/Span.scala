package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.elements.typeclasses.CanCopyWithNewProperty

object Span {

  given canCopySpanWithNewClassName: CanCopyWithNewProperty[Span, ClassName] with
    override def apply(newProperty: ClassName)(element: Span): Span =
      element.copy(className = newProperty)

  given canCopySpanWithNewID: CanCopyWithNewProperty[Span, ID] with
    override def apply(newProperty: ID)(element: Span): Span =
      element.copy(id = newProperty)

  given canCopySpanWithNewChilds: CanCopyWithNewProperty[Span, Childs] with
    override def apply(newProperty: Childs)(element: Span): Span =
      element.copy(childs = newProperty)

}

case class Span(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends Element
