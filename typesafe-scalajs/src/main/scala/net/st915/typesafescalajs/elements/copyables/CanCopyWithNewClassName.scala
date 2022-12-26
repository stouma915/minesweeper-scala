package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.ClassName

trait CanCopyWithNewClassName[A] {

  def copyWith(newProperty: ClassName): A

}
