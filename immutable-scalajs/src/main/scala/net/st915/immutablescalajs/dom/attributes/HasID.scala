package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.properties.ID

trait HasID[A] {

  def id: Option[ID]

  def copyWithNewID(newID: Option[ID]): A

}
