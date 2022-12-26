package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.typeclasses.CanCopyWithNewProperty

object Div {

  given canCopyDivWithNewClassName: CanCopyWithNewProperty[Div, ClassName] with
    override def apply(newProperty: ClassName)(element: Div): Div =
      element.copy(className = newProperty)

  given canCopyDivWithNewID: CanCopyWithNewProperty[Div, ID] with
    override def apply(newProperty: ID)(element: Div): Div =
      element.copy(id = newProperty)

  given canCopyDivWithNewChilds: CanCopyWithNewProperty[Div, Childs] with
    override def apply(newProperty: Childs)(element: Div): Div =
      element.copy(childs = newProperty)

}

case class Div(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends Element
