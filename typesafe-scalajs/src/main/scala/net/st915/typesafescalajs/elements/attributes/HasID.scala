package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.ID

trait HasID[A] {

  def id: ID

  def copyWith(newProperty: ID): A

}
