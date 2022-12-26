package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.elements.typeclasses.CanCopyWithNewProperty

object BR {

  given canCopyBRWithNewClassName: CanCopyWithNewProperty[BR, ClassName] with
    override def apply(newProperty: ClassName)(element: BR): BR =
      element.copy(className = newProperty)

  given canCopyBRWithNewID: CanCopyWithNewProperty[BR, ID] with
    override def apply(newProperty: ID)(element: BR): BR =
      element.copy(id = newProperty)

  given canCopyBRWithNewChilds: CanCopyWithNewProperty[BR, Childs] with
    override def apply(newProperty: Childs)(element: BR): BR =
      element.copy(childs = newProperty)

}

case class BR(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty
) extends Element
