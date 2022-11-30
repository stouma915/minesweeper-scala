package net.st915.minesweeper.syntax

import org.scalajs.dom.{Element, HTMLBRElement, HTMLDocument}

trait HTMLDocumentSyntax {

  implicit class HTMLDocumentOps(document: HTMLDocument) {

    def createElementWithType[A <: Element](name: String): A =
      document
        .createElement(name)
        .asInstanceOf[A]

    def makeBR: HTMLBRElement = createElementWithType[HTMLBRElement]("br")

  }

}
