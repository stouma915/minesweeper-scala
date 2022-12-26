package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.Childs

trait HasChilds[A] {

  def childs: Childs

  def copyWith(newProperty: Childs): A

}
