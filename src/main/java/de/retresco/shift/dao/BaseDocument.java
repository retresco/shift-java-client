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
import de.retresco.shift.serialize.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

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
    @NotNull
    private String url;

    /**
     * The object's list of sources.
     * <p/>
     * A source can be a news agency (DPA) or one of your own entities.
     */
    @NotNull
    @Size(min = 1)
    private List<String> source;

    /**
     * Some copyright information.
     */
    private String copyright;

    /**
     * The list of publications an item belongs to.
     */
    private List<String> publication;

    /**
     * Indicate when the item should be published, i.e. be available for widgets.
     */
    @NotNull
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime timestamp;

    /**
     * Indicate when the item should be published, i.e. be available for widgets.
     */
    @JsonSerialize(using = DateTimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime startdate;

    /**
     * Indicate when the item should be unpublished, i.e. not be available anymore.
     */
    @JsonSerialize(using = DateTimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
    private DateTime enddate;

    /**
     * The item's time to live, i.e. after that many seconds it will automatically be removed.
     */
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
    private List<String> location;

    /**
     * List of arbitrary tags.
     */
    private List<String> tags;

    /**
     * Indicate if this item is paid content.
     */
    private Boolean paidcontent = null;

    /**
     * Subressort information.
     */
    private List<String> subressort;

    /**
     * Author of the document if any.
     */
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<String> getPublication() {
        return publication;
    }

    public void setPublication(List<String> publication) {
        this.publication = publication;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public DateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(DateTime startdate) {
        this.startdate = startdate;
    }

    public DateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(DateTime enddate) {
        this.enddate = enddate;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getPaidcontent() {
        return paidcontent;
    }

    public void setPaidcontent(Boolean paidcontent) {
        this.paidcontent = paidcontent;
    }

    public List<String> getSubressort() {
        return subressort;
    }

    public void setSubressort(List<String> subressort) {
        this.subressort = subressort;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}