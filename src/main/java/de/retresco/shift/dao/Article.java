/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    private ArticleImage image;

    /**
     * List of categories (Ressorts) for this article.
     */
    @Getter
    @Setter
    @Size(min = 1)
    @NotNull
    private List<String> ressort;

}