package com.example

import scala.xml.Node

/**
  * Created by lichlyterklein on 9/5/16.
  */
case class Podcast (
  title: String,
  link: String,
  language: String,
  copyright: String,
  description: String
)

object Podcast {

  def fromXml(node: Node):Podcast = {
    val title = (node \ "title").text
    val link = (node \ "link").text
    val language = (node \ "language").text
    val copyright = (node \ "copyright").text
    val description = (node \ "description").text
    new Podcast(title, link, language, copyright, description)
  }

}