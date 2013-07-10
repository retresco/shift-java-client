package de.retresco.shift.dao;

import junit.framework.TestCase;
import org.codehaus.jackson.JsonGenerationException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;

/**
 * Test whether the {@link Article} implementation behaves as expected.
 */
public class ArticleTest extends TestCase{

    private final static Article dummyArticle() {
        final Article article = new Article();
        article.setTitle("Ein Titel");
        article.setTeaser("Ein Teaser");
        article.setBody("<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>");

        return article;
    }

    @Test
    public void testSimpleArticleSerialization() throws IOException {
        final Article testArticle = dummyArticle();
        assertEquals("{\"paidcontent\":false,\"title\":\"Ein Titel\"," +
                "\"teaser\":\"Ein Teaser\",\"body\":\"<p>Ein richtig langer Artikel in dem es um " +
                "Alles und Nichts geht!</p>\"}", testArticle.toJson());
    }

    @Test
    public void testSimpleArticlesWithDates() throws IOException {
        final Article testArticle = dummyArticle();
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"paidcontent\":false,\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"body\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithImage() throws IOException {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        testArticle.setImage(image);

        assertEquals("{\"paidcontent\":false,\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"body\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"caption\":\"Ein Bild zum Artikel\",\"as_separate_image\":true}}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithImageAndExpiration() throws IOException {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        testArticle.setImage(image);
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{{\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"paidcontent\":false,\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"body\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"caption\":\"Ein Bild zum Artikel\",\"as_separate_image\":true}}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithDetailedImage() throws IOException {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        image.setCopyright("Achim Menzel");
        image.setAsSeperateImage(false);
        image.setAuthor("Achim Menzel");
        testArticle.setImage(image);
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{{\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"paidcontent\":false,\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"body\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"copyright\":\"Achim Menzel\",\"author\":\"Achim Menzel\"," +
                "\"caption\":\"Ein Bild zum Artikel\",\"as_separate_image\":true}}",
                testArticle.toJson());
    }
}