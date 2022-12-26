package net.st915.typesafescalajs.nodes

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.nodes.attributes.HasInnerText
import net.st915.typesafescalajs.nodes.properties.InnerText
import net.st915.typesafescalajs.typeclasses.CanCopyWithNewProperty

object TextNode {

  given canCopyTextNodeWithNewInnerText: CanCopyWithNewProperty[TextNode, InnerText] with
    override def apply(newProperty: InnerText)(element: TextNode): TextNode =
      element.copy(innerText = newProperty)

}

case class TextNode(
  override val innerText: InnerText = Monoid[InnerText].empty
) extends Node with HasInnerText
