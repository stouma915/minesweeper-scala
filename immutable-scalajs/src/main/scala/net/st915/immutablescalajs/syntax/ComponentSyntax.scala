package net.st915.immutablescalajs.syntax

import net.st915.immutablescalajs.Component
import net.st915.immutablescalajs.dom.HTMLDivElement

trait ComponentSyntax {

  implicit class HTMLDivElementOps(element: HTMLDivElement) {

    def asComponent: Component = Component(element)

  }

}
