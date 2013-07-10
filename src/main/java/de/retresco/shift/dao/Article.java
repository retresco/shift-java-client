/**
 *
 */
package de.retresco.shift.dao;

import lombok.Getter;
import lombok.Setter;

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
    private String title;

    /**
     * Teaser.
     */
    @Getter
    @Setter
    private String teaser;

    /**
     * The article body.
     */
    @Getter
    @Setter
    private String body;

    /**
     * The image for this article.
     */
    @Getter
    @Setter
    private ArticleImage image;
}