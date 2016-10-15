import com.example.Podcast
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source
import scala.xml.XML

/**
  * Created by lichlyterklein on 9/5/16.
  */
class PodcastSpec extends FlatSpec with Matchers {

  "Podcast" should "created from xml" in {
    val feed = <rss>
      <title>Test Podcast</title>
      <description>Description</description>
      <language>en-us</language>
      <copyright>2016 Scala Spect</copyright>
      <link>Test Link</link>
      <item>
        <title>Test title</title>
        <description>Test description</description>
        <link>http://test.com</link>
        <author>not the itunes author</author>
        <itunes:author>scalatest</itunes:author>
        <itunes:subtitle>"Great episode"</itunes:subtitle>
        <enclosure url="http://test.com/file.mp3" type="audio/mp3" length="47217601"/>
      <itunes:image href="https://test.com/img.jpg"/>
      </item>
    </rss>

    val podcast = Podcast.fromXml(feed)
    podcast.title shouldBe "Test Podcast"
    assert(podcast.episodes.length == 1)

  }

  "Podcast" should "create from a file" in {
    val feed = XML.load("http://feeds.theincomparable.com/theincomparable")
    val podcast = Podcast.fromXml(feed \ "channel")
    podcast.title shouldBe "The Incomparable"
    assert(podcast.episodes.length > 1)

  }

}
