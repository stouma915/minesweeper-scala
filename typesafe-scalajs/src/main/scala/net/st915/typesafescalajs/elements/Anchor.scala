package net.st915.typesafescalajs.elements

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.attributes.HasHyperlink
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.typeclasses.CanCopyWithNewProperty

object Anchor {

  given canCopyAnchorWithNewClassName: CanCopyWithNewProperty[Anchor, ClassName] with
    override def apply(newProperty: ClassName)(element: Anchor): Anchor =
      element.copy(className = newProperty)

  given canCopyAnchorWithNewID: CanCopyWithNewProperty[Anchor, ID] with
    override def apply(newProperty: ID)(element: Anchor): Anchor =
      element.copy(id = newProperty)

  given canCopyAnchorWithNewChilds: CanCopyWithNewProperty[Anchor, Childs] with
    override def apply(newProperty: Childs)(element: Anchor): Anchor =
      element.copy(childs = newProperty)

  given canCopyAnchorWithNewHyperlink: CanCopyWithNewProperty[Anchor, Link] with
    override def apply(newProperty: Link)(element: Anchor): Anchor =
      element.copy(href = newProperty)

}

case class Anchor(
  override val className: ClassName = Monoid[ClassName].empty,
  override val id: ID = Monoid[ID].empty,
  override val childs: Childs = Monoid[Childs].empty,
  override val href: Link = Monoid[Link].empty
) extends Element with HasHyperlink
