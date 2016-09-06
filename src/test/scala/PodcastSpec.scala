import com.example.Podcast
import org.scalatest.{FlatSpec, Matchers}

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
    </rss>

    val podcast = Podcast.fromXml(feed)
    podcast.title shouldBe "Test Podcast"
  }

}
