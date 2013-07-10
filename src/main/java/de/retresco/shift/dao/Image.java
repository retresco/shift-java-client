/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A SHIFT image.
 */
public class Image extends BaseDocument {

    /**
     * Image caption.
     */
    @Getter
    @Setter
    private String caption;

}
