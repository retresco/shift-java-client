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

import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Embedded images in articles.
 */
public class ArticleImage extends ShiftDao {

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
     * Image caption.
     */
    private String caption;

    /**
     * Indicates, whether the image should be imported as a standalone image. If set to {@code true},
     * the image will be available for image only widgets like image galleries, e.g.
     */
    @JsonProperty("as_separate_image")
    private boolean asSeparateImage = true;

    /**
     * Author of the document if any.
     */
    private String author;

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isAsSeparateImage() {
        return asSeparateImage;
    }

    public void setAsSeparateImage(boolean asSeparateImage) {
        this.asSeparateImage = asSeparateImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
