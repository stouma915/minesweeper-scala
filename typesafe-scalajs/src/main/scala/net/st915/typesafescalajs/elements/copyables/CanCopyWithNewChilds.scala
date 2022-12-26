package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.Childs

trait CanCopyWithNewChilds[A] {

  def copyWith(newProperty: Childs): A

}
