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
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 7/12/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
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