package net.st915.typesafescalajs.nodes

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.nodes.attributes.HasInnerText
import net.st915.typesafescalajs.nodes.properties.InnerText

object TextNode {

  def apply(content: String): TextNode = TextNode(InnerText(content))

}

case class TextNode(
  override val innerText: InnerText = Monoid[InnerText].empty
) extends Node with HasInnerText[TextNode] {

  override def copyWith(newProperty: InnerText): TextNode =
    copy(innerText = newProperty)

}
