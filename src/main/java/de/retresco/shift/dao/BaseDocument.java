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

import de.retresco.shift.exceptions.ShiftClientException;
import de.retresco.shift.exceptions.ShiftDataViolation;
import de.retresco.shift.serialize.DateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

/**
 * Base document for SHIFT related data.
 */
public class BaseDocument extends ShiftDao {

    /**
     * The jackson object mapper.
     */
    protected final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    /**
     * Bean validator.
     */
    protected final static Validator VALIDATOR;

    static {
        final ValidatorFactory f = Validation.buildDefaultValidatorFactory();
        VALIDATOR = f.getValidator();
    }

    /**
     * The object's URL.
     */
    @Getter
    @Setter
    @NotNull
    private String url;

    /**
     * The object's list of sources.
     * <p/>
     * A source can be a news agency (DPA) or one of your own entities.
     */
    @Getter
    @Setter
    @NotNull
    @Size(min = 1)
    private List<String> source;

    /**
     * Some copyright information.
     */
    @Getter
    @Setter
    private String copyright;

    /**
     * The list of publications an item belongs to.
     */
    @Getter
    @Setter
    private List<String> publication;

    /**
     * Indicate when the item should be published, i.e. be available for widgets.
     */
    @Getter
    @Setter
    @NotNull
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime timestamp;

    /**
     * Indicate when the item should be published, i.e. be available for widgets.
     */
    @Getter
    @Setter
    @JsonSerialize(using = DateTimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime startdate;

    /**
     * Indicate when the item should be unpublished, i.e. not be available anymore.
     */
    @Getter
    @Setter
    @JsonSerialize(using = DateTimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime enddate;

    /**
     * The item's time to live, i.e. after that many seconds it will automatically be removed.
     */
    @Getter
    @Setter
    private Long ttl;

    /**
     * Location information as a list of "lat,lon" pairs.
     * <p/>
     * An example:
     * <code>
     * final List<String> location = new ArrayList<String>();
     * location.add("52.516285,13.377694")
     * location.add("52.520254,13.369165")
     * doc.setLocation(location)
     * </code>
     */
    @Getter
    @Setter
    private List<String> location;

    /**
     * List of arbitrary tags.
     */
    @Getter
    @Setter
    private List<String> tags;

    /**
     * Indicate if this item is paid content.
     */
    @Getter
    @Setter
    private Boolean paidcontent = null;

    /**
     * Subressort information.
     */
    @Getter
    @Setter
    private List<String> subressort;

    /**
     * Author of the document if any.
     */
    @Getter
    @Setter
    private String author;

    /**
     * Compute the SHIFT item id.
     *
     * @return
     * @throws ShiftClientException Thrown if the <code>SHA-1</code> digest is not found!
     */
    @JsonIgnore
    public final String getItemId() throws ShiftClientException {
        try {
            final MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hash = sha1.digest(this.getUrl().getBytes(Charset.forName("UTF-8")));
            final Formatter formatter = new Formatter();
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (final NoSuchAlgorithmException e) {
            throw new ShiftClientException(e);
        }
    }

}