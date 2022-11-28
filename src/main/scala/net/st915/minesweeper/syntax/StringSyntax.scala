package net.st915.minesweeper.syntax

import org.scalajs.dom.*

trait StringSyntax {

  implicit class StringOps(string: String) {

    def textNode(implicit doc: Document): Text =
      doc.createTextNode(string)

  }

}
