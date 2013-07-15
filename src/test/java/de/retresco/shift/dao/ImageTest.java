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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple tests for image serialization.
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