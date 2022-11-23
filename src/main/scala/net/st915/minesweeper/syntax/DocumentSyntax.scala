package net.st915.minesweeper.syntax

import org.scalajs.dom.{Document, Element}

trait DocumentSyntax {

  implicit class DocumentOps(doc: Document) {

    def createElementWithType[A >: Element](name: String): A =
      doc
        .createElement(name)
        .asInstanceOf[A]

  }

}
