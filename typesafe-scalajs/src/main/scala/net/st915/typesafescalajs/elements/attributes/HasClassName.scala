package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.ClassName

trait HasClassName[A] {

  def className: ClassName

  def copyWith(newProperty: ClassName): A

}
