/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;
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
     * Image caption.
     */
    @Getter
    @Setter
    @NotNull
    private String caption;

    /**
     * Indicates, whether the image should be imported as a standalone image. If set to {@code true},
     * the image will be available for image only widgets like image galleries, e.g.
     */
    @Getter
    @Setter
    @JsonProperty("as_separate_image")
    private boolean asSeparateImage = true;

    /**
     * Author of the document if any.
     */
    @Getter
    @Setter
    private String author;

}
