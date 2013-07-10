/**
 *
 */
package de.retresco.shift.dao;

import com.apple.crypto.provider.MessageDigestSHA1;
import de.retresco.shift.exceptions.ShiftClientException;
import de.retresco.shift.serialize.DateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

/**
 * Base document for SHIFT related data.
 */
public class BaseDocument {

    /**
     * The jackson object mapper.
     */
    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    /**
     * The object's URL.
     */
    @Getter
    @Setter
    private String url;

    /**
     * The object's list of sources.
     * <p/>
     * A source can be a news agency (DPA) or one of your own entities.
     */
    @Getter
    @Setter
    private List<String> sources;

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
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime startdate = null;

    /**
     * Indicate when the item should be unpublished, i.e. not be available anymore.
     */
    @Getter
    @Setter
    @JsonSerialize(using = DateTimeSerializer.class)
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
    private boolean paidcontent;

    /**
     * List of categories (Ressorts) for this article.
     */
    @Getter
    @Setter
    private List<String> ressort;

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
     * Convert an instance of one of the DAOs to their JSON representation.
     *
     * @return The json string of this instance.
     *
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
        return BaseDocument.MAPPER.writeValueAsString(this);
    }

}