package net.st915.immutablescalajs.dom.attributes

import cats.effect.IO

trait RightClickable[A] {

  def onRightClick: IO[Unit]

  def copyWithNewRightClickEvent(newRightClickEvent: IO[Unit]): A

}
