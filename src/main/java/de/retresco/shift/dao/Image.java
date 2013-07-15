/**
 *
 */
package de.retresco.shift.dao;

import de.retresco.shift.exceptions.ShiftDataViolation;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    /**
     * Convert an instance of one of the DAOs to their JSON representation.
     *
     * @return The json string of this instance.
     * @throws org.codehaus.jackson.JsonGenerationException
     * @throws org.codehaus.jackson.map.JsonMappingException
     * @throws java.io.IOException
     */
    public String toJson() throws JsonGenerationException, JsonMappingException, IOException, ShiftDataViolation {
        final Set<ConstraintViolation<Image>> violations = VALIDATOR.validate(this);
        if (violations.size() > 0) {
            for (final ConstraintViolation<Image> violation : violations) {
                throw new ShiftDataViolation(violation);
            }
        }
        return MAPPER.writeValueAsString(this);
    }

}