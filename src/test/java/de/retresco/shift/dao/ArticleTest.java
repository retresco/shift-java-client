/**
 * Copyright 2013 Retresco GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.retresco.shift.dao;

import de.retresco.shift.exceptions.ShiftDataViolation;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Test whether the {@link Article} implementation behaves as expected.
 */
public class ArticleTest extends TestCase {

    private final static Article dummyArticle() {
        final Article article = new Article();
        article.setTitle("Ein Titel");
        article.setTeaser("Ein Teaser");
        article.setHtml("<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>");
        article.setTimestamp(new DateTime(2012, 12, 13, 14, 15));
        article.setUrl("http://www.google.de/article.html");
        article.setRessort(Arrays.asList("Politik"));
        article.setSource(Arrays.asList("me"));

        return article;
    }

    @Test
    public void testSimpleArticleSerialization() throws IOException, ShiftDataViolation {
        final Article testArticle = dummyArticle();
        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\"," +
                "\"title\":\"Ein Titel\"," +
                "\"teaser\":\"Ein Teaser\",\"html\":\"<p>Ein richtig langer Artikel in dem es um " +
                "Alles und Nichts geht!</p>\",\"ressort\":[\"Politik\"]}", testArticle.toJson());
    }

    @Test
    public void testSimpleArticlesWithDates() throws IOException, ShiftDataViolation {
        final Article testArticle = dummyArticle();
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\"," +
                "\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"html\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"ressort\":[\"Politik\"]}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithImage() throws IOException, ShiftDataViolation {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        image.setUrl("http://www.google.de/image.jpg");
        image.setSource(Arrays.asList("me"));
        testArticle.setImage(image);

        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\"," +
                "\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"html\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"url\":\"http://www.google.de/image.jpg\",\"source\":[\"me\"]," +
                "\"caption\":\"Ein Bild zum Artikel\"," +
                "\"as_separate_image\":true}," +
                "\"ressort\":[\"Politik\"]}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithImageAndExpiration() throws IOException, ShiftDataViolation {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        image.setUrl("http://www.google.de/image.jpg");
        image.setSource(Arrays.asList("me"));
        testArticle.setImage(image);
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\"," +
                "\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"html\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"url\":\"http://www.google.de/image.jpg\",\"source\":[\"me\"]," +
                "\"caption\":\"Ein Bild zum Artikel\"," +
                "\"as_separate_image\":true}," +
                "\"ressort\":[\"Politik\"]}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithDetailedImage() throws IOException, ShiftDataViolation {
        final Article testArticle = dummyArticle();
        final ArticleImage image = new ArticleImage();
        image.setCaption("Ein Bild zum Artikel");
        image.setCopyright("Achim Menzel");
        image.setAsSeparateImage(false);
        image.setAuthor("Achim Menzel");
        image.setUrl("http://www.google.de/image.jpg");
        image.setSource(Arrays.asList("me"));
        testArticle.setImage(image);
        testArticle.setStartdate(new DateTime(2012, 12, 12, 13, 15));
        testArticle.setEnddate(new DateTime(2013, 12, 12, 13, 15));

        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\"," +
                "\"startdate\":\"2012-12-12T13:15:00.000\",\"enddate\":\"2013-12-12T13:15:00.000\"," +
                "\"title\":\"Ein Titel\",\"teaser\":\"Ein Teaser\"," +
                "\"html\":\"<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>\"," +
                "\"image\":{\"url\":\"http://www.google.de/image.jpg\",\"source\":[\"me\"]," +
                "\"copyright\":\"Achim Menzel\"," +
                "\"caption\":\"Ein Bild zum Artikel\",\"author\":\"Achim Menzel\",\"as_separate_image\":false}," +
                "\"ressort\":[\"Politik\"]}",
                testArticle.toJson());
    }

    @Test
    public void testArticleWithoutTimestamp() throws IOException {
        final Article testArticle = dummyArticle();
        testArticle.setTimestamp(null);
        try {
            testArticle.toJson();
            assertTrue("This should not be reached!", false);
        } catch (ShiftDataViolation e) {
            assertEquals("timestamp may not be null", e.getMessage());
        }
    }

    @Test
    public void testArticleWithForeignId() throws ShiftDataViolation, IOException {
        final Article testArticle = dummyArticle();
        testArticle.setForeignId("test123");
        assertEquals("{\"url\":\"http://www.google.de/article.html\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-12-13T14:15:00.000\",\"title\":\"Ein Titel\"," +
                "\"teaser\":\"Ein Teaser\",\"html\":\"<p>Ein richtig langer Artikel in dem es um " +
                "Alles und Nichts geht!</p>\",\"foreignId\":\"test123\",\"ressort\":" +
                        "[\"Politik\"]}", testArticle.toJson());
    }

}