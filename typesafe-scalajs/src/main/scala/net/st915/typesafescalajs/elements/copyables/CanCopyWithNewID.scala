package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.ID

trait CanCopyWithNewID[A] {

  def copyWith(newProperty: ID): A

}
