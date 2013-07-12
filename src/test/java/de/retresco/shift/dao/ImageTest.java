package de.retresco.shift.dao;

import de.retresco.shift.exceptions.ShiftDataViolation;
import junit.framework.TestCase;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 7/12/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageTest extends TestCase {

    private final static Image dummyImage() {
        final Image image = new Image();
        image.setCaption("A simple caption");
        image.setUrl("http://www.google.de/logo.png");
        image.setTimestamp(new DateTime(2012, 11, 12, 13, 14));
        image.setSource(Arrays.asList("me"));
        return image;
    }

    @Test
    public void testSimpleImageSerialization() throws IOException, ShiftDataViolation {
        final Image image = dummyImage();
        assertEquals("{\"url\":\"http://www.google.de/logo.png\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-11-12T13:14:00.000\"," +
                "\"caption\":\"A simple caption\"}", image.toJson());
    }

    @Test
    public void testSimpleImageWithStartAndEndDate() throws ShiftDataViolation, IOException {
        final Image image = dummyImage();
        image.setStartdate(new DateTime(2012, 12, 12, 13, 20));
        image.setEnddate(new DateTime(2014, 12, 12, 13, 30));

        assertEquals("{\"url\":\"http://www.google.de/logo.png\",\"source\":[\"me\"]," +
                "\"timestamp\":\"2012-11-12T13:14:00.000\"," +
                "\"startdate\":\"2012-12-12T13:20:00.000\",\"enddate\":\"2014-12-12T13:30:00.000\"," +
                "\"caption\":\"A simple caption\"}", image.toJson());
    }

    @Test
    public void testSimpleImageSerializationWithErrors() throws IOException {
        final Image image = dummyImage();
        image.setTimestamp(null);
        try {
            image.toJson();
            assertTrue("This should not be reached!", false);
        } catch (final ShiftDataViolation e) {
            assertEquals("timestamp may not be null", e.getMessage());
        }
    }

}