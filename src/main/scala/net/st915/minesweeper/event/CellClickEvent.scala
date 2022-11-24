package net.st915.minesweeper.event

import net.st915.minesweeper.Coordinate

case class CellClickEvent(coord: Coordinate) extends Event
