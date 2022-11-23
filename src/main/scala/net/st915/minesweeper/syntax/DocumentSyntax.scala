package net.st915.minesweeper.syntax

import org.scalajs.dom.Document

trait DocumentSyntax {

  implicit class DocumentOps(doc: Document) {

    def createElementWithType[A](name: String): A =
      doc
        .createElement(name)
        .asInstanceOf[A]

  }

}
