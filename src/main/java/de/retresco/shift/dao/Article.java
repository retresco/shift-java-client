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
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * A SHIFT article.
 */
public class Article extends BaseDocument {

    /**
     * article title.
     */
    @Getter
    @Setter
    @NotNull
    private String title;

    /**
     * Teaser.
     */
    @Getter
    @Setter
    @NotNull
    private String teaser;

    /**
     * The article body.
     */
    @Getter
    @Setter
    private String html;

    /**
     * The image for this article.
     */
    @Getter
    @Setter
    @Valid
    private ArticleImage image;

    /**
     * List of categories (Ressorts) for this article.
     */
    @Getter
    @Setter
    @Size(min = 1)
    @NotNull
    private List<String> ressort;

    /**
     * Convert an instance of one of the DAOs to their JSON representation.
     *
     * @return The json string of this instance.
     * @throws org.codehaus.jackson.JsonGenerationException
     *
     * @throws org.codehaus.jackson.map.JsonMappingException
     *
     * @throws java.io.IOException
     */
    public String toJson() throws JsonGenerationException, JsonMappingException, IOException, ShiftDataViolation {
        final Set<ConstraintViolation<Article>> violations = VALIDATOR.validate(this);
        for (final ConstraintViolation<Article> violation : violations) {
            throw new ShiftDataViolation(violation);
        }
        return MAPPER.writeValueAsString(this);
    }

}