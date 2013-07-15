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
package de.retresco.shift.serialize;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Custom serializer for {@link org.joda.time.DateTime} objects into the expected format.
 */
public class DateTimeSerializer extends JsonSerializer<DateTime>{

    /**
     * The {@link org.joda.time.format.DateTimeFormatter} used for formatting the correct dates for SHIFT.
     */
    public final static DateTimeFormatter SHIFT_DATE_TIME = ISODateTimeFormat.dateHourMinuteSecondMillis();


    /**
     * Serialize a {@link DateTime} instance into a supported string representation.
     *
     * @param dateTime
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(final DateTime dateTime, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (dateTime != null) {
            jsonGenerator.writeString(SHIFT_DATE_TIME.print(dateTime));
        }
    }
}
