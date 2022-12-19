package net.st915.immutablescalajs

import net.st915.immutablescalajs.dom.HTMLDivElement

case class Component(raw: HTMLDivElement) {

  def unwrap: HTMLDivElement = raw

}
