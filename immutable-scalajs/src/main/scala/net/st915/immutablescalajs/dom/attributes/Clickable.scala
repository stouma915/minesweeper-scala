package net.st915.immutablescalajs.dom.attributes

import cats.effect.IO

trait Clickable[A] {

  def onClick: IO[Unit]

  def copyWithNewClickEvent(newClickEvent: IO[Unit]): A

}
