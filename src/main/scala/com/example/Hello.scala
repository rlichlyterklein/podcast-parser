package com.example

import scala.xml.XML


object Hello {
  def main(args: Array[String]): Unit = {
    val fileStream = getClass.getResourceAsStream("/theincomparable.xml")

    val feed = XML.load(fileStream)

    val items = feed \ "channel" \ "item"

    println(items.size)

    println("Hello, world!")
  }
}
