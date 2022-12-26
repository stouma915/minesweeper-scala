package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.Link

trait HasHyperlink[A] {

  def href: Link

  def copyWith(newProperty: Link): A

}
