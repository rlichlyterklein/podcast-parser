package com.example

import java.util

import scala.xml.{Node, NodeSeq}

/**
  * Created by lichlyterklein on 9/5/16.
  */
case class Podcast (
  title: String,
  link: String,
  language: String,
  copyright: String,
  description: String,
  episodes: Seq[PodcastEpisode]

)

case class PodcastEpisode (
  title: String,
  link: String,
  description: String,
  itunesAuthor: String,
  itunesSubtitle: String,
  itunesImage: String,
  enclosure: PodcastEnclosure
                          )

case class PodcastEnclosure (
  url: String,
  enclosureType: String,
  length: Long
)

object Podcast {

  def fromXml(node: NodeSeq):Podcast = {
    val title = (node \ "title").text
    val link = (node \ "link").text
    val language = (node \ "language").text
    val copyright = (node \ "copyright").text
    val description = (node \ "description").text
    //val items = Seq[PodcastEpisode];
    var items: Seq[PodcastEpisode] = Seq()
    for (item <- (node \ "item")) {
      val enclosure = item \ "enclosure"
      val podcastEnclosure = new PodcastEnclosure(
        (enclosure \ "@url").text,
        (enclosure \ "@type").text,
        (enclosure \ "@length").text.toLong)
      items = items :+ new PodcastEpisode(
        (item \ "title").text,
        (item \ "link").text,
        (item \ "description").text,
        (item \\ "author") filter (_.prefix == "itunes") text,
        (item \\ "subtitle") filter (_.prefix == "itunes") text,
        (item \\ "image" \ "@href").text,
        podcastEnclosure
      )
    }
    new Podcast(title, link, language, copyright, description, items)
  }

}

