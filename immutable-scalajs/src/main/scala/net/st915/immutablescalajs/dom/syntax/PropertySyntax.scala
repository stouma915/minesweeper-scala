package net.st915.immutablescalajs.dom.syntax

import net.st915.immutablescalajs.dom.properties.*

trait PropertySyntax {

  implicit class StringOps(str: String) {

    def asCSSClass: CSSClass = CSSClass(str)

    def asHyperlink: Hyperlink = Hyperlink(str)

    def asID: ID = ID(str)

    def asText: Text = Text(str)

  }

  implicit class StringListOps(strList: List[String]) {

    def asCSSClass: CSSClass = CSSClass(strList: _*)

  }

}
