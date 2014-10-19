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

import de.retresco.shift.exceptions.ShiftDataViolation;
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
    @NotNull
    private String caption;

    /**
     * List of categories (Ressorts) for this article.
     */
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getRessort() {
        return ressort;
    }

    public void setRessort(List<String> ressort) {
        this.ressort = ressort;
    }
}