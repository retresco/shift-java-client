/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Embedded images in articles.
 */
public class ArticleImage extends Image {

    /**
     * Indicates, whether the image should be imported as a standalone image. If set to {@code true},
     * the image will be available for image only widgets like image galleries, e.g.
     */
    @Getter
    @Setter
    @JsonProperty("as_separate_image")
    private boolean asSeperateImage = false;

}
