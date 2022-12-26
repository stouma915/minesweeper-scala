package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.typeclasses.CanCopyWithNewProperty

object H1 {

  given canCopyH1WithNewClassName: CanCopyWithNewProperty[H1, ClassName] with
    override def apply(newProperty: ClassName)(element: H1): H1 =
      element.copy(className = newProperty)

  given canCopyH1WithNewID: CanCopyWithNewProperty[H1, ID] with
    override def apply(newProperty: ID)(element: H1): H1 =
      element.copy(id = newProperty)

  given canCopyH1WithNewChilds: CanCopyWithNewProperty[H1, Childs] with
    override def apply(newProperty: Childs)(element: H1): H1 =
      element.copy(childs = newProperty)

}

case class H1(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends Element
