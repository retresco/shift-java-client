/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A SHIFT image.
 */
public class Image extends BaseDocument {

    /**
     * Image caption.
     */
    @Getter
    @Setter
    @NotNull
    private String caption;

    /**
     * List of categories (Ressorts) for this article.
     */
    @Getter
    @Setter
    private List<String> ressort;

}
