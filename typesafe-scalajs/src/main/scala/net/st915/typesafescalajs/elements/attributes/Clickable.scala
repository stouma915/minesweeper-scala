package net.st915.typesafescalajs.elements.attributes

import cats.effect.IO
import net.st915.typesafescalajs.elements.properties.ClickEvent

trait Clickable {

  def clickEvent: ClickEvent

}
