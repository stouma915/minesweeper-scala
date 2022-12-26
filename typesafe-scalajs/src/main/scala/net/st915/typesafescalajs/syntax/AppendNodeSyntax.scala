package net.st915.typesafescalajs.syntax

import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.copyables.CanCopyWithNewChilds
import net.st915.typesafescalajs.elements.properties.Childs

trait AppendNodeSyntax {

  implicit class CanCopyWithNewChildsOps[A <: CanCopyWithNewChilds[A]](element: A) {

    def with_(newChilds: Childs): A = element.copyWith(newChilds)

    def with_(newChilds: List[Node]): A = with_(Childs(newChilds))

    def with_(newChilds: Node*): A = with_(newChilds.toList)

  }

}
