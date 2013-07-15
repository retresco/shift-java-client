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
package de.retresco.shift;

import de.retresco.shift.dao.Article;
import de.retresco.shift.dao.Image;
import de.retresco.shift.exceptions.ShiftClientException;
import de.retresco.shift.exceptions.ShiftDataViolation;
import junit.framework.TestCase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * Simple tests demonstrating the usage.
 */
public class ShiftClientTest extends TestCase {

    @Ignore
    @Test
    public void testSimpleArticle() throws ShiftClientException, ShiftDataViolation {
        final ShiftClient client = new ShiftClient(false, "localhost:14180", "meo3SgyuwhFwSFQZn+6KuZbg",
                "Rrif01wpOrhegSC8SbkmahJu", new DefaultHttpClient());
        final Article article = new Article();
        article.setTitle("Ein Titel");
        article.setTeaser("Ein Teaser");
        article.setHtml("<p>Ein richtig langer Artikel in dem es um Alles und Nichts geht!</p>");
        article.setTimestamp(new DateTime(2012, 12, 13, 14, 15));
        article.setUrl("http://www.google.de/article.html");
        article.setRessort(Arrays.asList("Politik"));
        article.setSource(Arrays.asList("me"));

        assertTrue(client.addArticle(article));

    }

    @Ignore
    @Test
    public void testSimpleImage() throws ShiftClientException, ShiftDataViolation {
        final ShiftClient client = new ShiftClient(false, "localhost:14180", "meo3SgyuwhFwSFQZn+6KuZbg",
                "Rrif01wpOrhegSC8SbkmahJu", new DefaultHttpClient());
        final Image image = new Image();
        image.setCaption("A caption");
        image.setTimestamp(new DateTime(2012, 12, 13, 14, 15));
        image.setRessort(Arrays.asList("Sport"));
        image.setUrl("http://www.google.de/image.jpg");
        image.setSource(Arrays.asList("me"));

        assertTrue(client.addImage(image));
    }

    @Ignore
    @Test
    public void testImageRemoval() throws ShiftClientException {
        final ShiftClient client = new ShiftClient(false, "localhost:14180", "meo3SgyuwhFwSFQZn+6KuZbg",
                "Rrif01wpOrhegSC8SbkmahJu", new DefaultHttpClient());
        final Image image = new Image();
        image.setUrl("http://www.google.de/image.jpg");
        assertTrue(client.removeImage(image));
    }

}