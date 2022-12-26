package net.st915.typesafescalajs.elements

import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.attributes.*

trait Element extends Node
    with HasClassName
    with HasID
    with HasChilds
