package net.st915.minesweeper.syntax

import org.scalajs.dom.{HTMLDocument, Text}

trait StringSyntax {

  implicit class StringOps(string: String) {

    def textNode(implicit document: HTMLDocument): Text =
      document.createTextNode(string)

  }

}
