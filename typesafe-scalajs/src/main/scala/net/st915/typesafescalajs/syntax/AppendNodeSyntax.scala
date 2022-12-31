package net.st915.typesafescalajs.syntax

import cats.Monoid
import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.attributes.HasChildren
import net.st915.typesafescalajs.elements.properties.Children

trait AppendNodeSyntax {

  implicit class HasChildrenOps[A <: HasChildren[A]](element: A) {

    def with_(newChildren: Children): A =
      element.copyWith(Monoid[Children].combine(element.children, newChildren))

    def with_(newChildren: List[Node]): A = with_(Children(newChildren))

    def with_(newChildren: Node*): A = with_(newChildren.toList)

  }

}
