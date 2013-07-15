/**
 *
 */
package de.retresco.shift;

import de.retresco.shift.dao.Article;
import de.retresco.shift.dao.Image;
import de.retresco.shift.exceptions.ShiftClientException;
import de.retresco.shift.exceptions.ShiftDataViolation;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * The SHIFT client.
 */
public class ShiftClient {

    /**
     * The Content Pool API for articles.
     */
    private final String articleUrl;

    /**
     * The Content Pool API for images.
     */
    private final String imageUrl;

    /**
     * The {@link OAuthConsumer} used to sign requests.
     */
    private final OAuthConsumer oAuthConsumer;

    /**
     * The client used for communicating to the SHIFT Import API.
     */
    private final HttpClient httpClient;

    /**
     * Constructing the default SHIFT Client.
     *
     * @param apiKey Your SHIFT API key
     * @param apiSecret Your SHIFT API secret
     */
    public ShiftClient(final String apiKey, final String apiSecret) {
        this(apiKey, apiSecret, false);
    }

    /**
     * Constructing the SHIT client with optional HTTPS transport.
     *
     * @param apiKey Your SHIFT API key
     * @param apiSecret Your SHIFT API secret
     * @param secure If {@code true} use HTTPS transport, otherwise HTTP is used.
     */
    public ShiftClient(final String apiKey, final String apiSecret, final boolean secure) {
        this(secure, "shift.retresco.de/contentpool/", apiKey, apiSecret, new DefaultHttpClient());
    }

    /**
     * Constructor setting all necessary values needed to talk to the SHIFT Import API.
     *
     * @param apiHost
     * @param apiKey
     * @param apiSecret
     */
    ShiftClient(final boolean secure, final String apiHost, final String apiKey, final String apiSecret,
                final HttpClient httpClient) {
        final String scheme = secure ? "https://" : "http://";
        this.articleUrl = scheme + apiHost + "/contentpool/article/";
        this.imageUrl = scheme + apiHost + "/contentpool/image/";

        this.oAuthConsumer = new CommonsHttpOAuthConsumer(apiKey, apiSecret);
        this.oAuthConsumer.setTokenWithSecret(null, "");
        this.httpClient = httpClient;
    }

    /**
     * Sign and execute the HTTP request.
     *
     * @param request The request to execute.
     * @throws ShiftClientException Thrown if there was any error executing the request.
     */
    HttpResponse executeRequest(final HttpUriRequest request) throws ShiftClientException {
        try {
            this.oAuthConsumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            throw new ShiftClientException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new ShiftClientException(e);
        } catch (OAuthCommunicationException e) {
            throw new ShiftClientException(e);
        }

        try {
            return this.httpClient.execute(request);
        } catch (IOException e) {
            throw new ShiftClientException(e);
        }
    }

    /**
     * Add a new article to the SHIFT backend.
     *
     * @param article The article to add.
     * @throws ShiftClientException Thrown, if there was any error talking to SHIFT. The original cause is
     *                              initialized correctly.
     * @throws ShiftDataViolation Upon serialization errors for the article
     */
    public boolean addArticle(final Article article) throws ShiftClientException, ShiftDataViolation {
        final HttpPost post = new HttpPost(this.articleUrl);

        try {
            final HttpEntity body = new StringEntity(article.toJson(), ContentType.APPLICATION_JSON);
            post.setEntity(body);
        } catch (final IOException e) {
            throw new ShiftClientException(e);
        }

        final HttpResponse response = executeRequest(post);
        return response.getStatusLine().getStatusCode() == 200;
    }

    /**
     * Remove an existing article from SHIFT.
     *
     * @param article The article to remove
     * @return {@code true} if successful, {@code false} otherwise.
     * @throws ShiftClientException
     */
    public boolean removeArticle(final Article article) throws ShiftClientException {
        return this.removeImageById(article.getItemId());
    }

    /**
     * Remove an existing article from SHIFT.
     *
     * @param articleId The article ID to remove
     * @return {@code true} if successful, {@code false} otherwise.
     * @throws ShiftClientException
     */
    public boolean removeArticleById(final String articleId) throws ShiftClientException {
        final HttpDelete delete = new HttpDelete(this.articleUrl + articleId);
        final HttpResponse response = executeRequest(delete);

        return response.getStatusLine().getStatusCode() == 204;
    }

    /**
     * Add a new image to the SHIFT backend.
     *
     * @param image The image to add.
     */
    public boolean addImage(final Image image) throws ShiftClientException, ShiftDataViolation {
        final HttpPost post = new HttpPost(this.imageUrl);

        try {
            final HttpEntity body = new StringEntity(image.toJson(), ContentType.APPLICATION_JSON);
            post.setEntity(body);
        } catch (final IOException e) {
            // TODO throw specialized exception
            throw new ShiftClientException(e);
        }

        final HttpResponse response = executeRequest(post);
        return response.getStatusLine().getStatusCode() == 200;
    }

    /**
     * Remove an existing image from SHIFT.
     *
     * @param image The image to remove.
     * @return {@code true} if successful, {@code false} otherwise.
     * @throws ShiftClientException
     */
    public boolean removeImage(final Image image) throws ShiftClientException {
        return this.removeImageById(image.getItemId());
    }

    /**
     * Remove an existing image from SHIFT.
     *
     * @param imageId The image ID to remove.
     * @return {@code true} if successful, {@code false} otherwise.
     * @throws ShiftClientException
     */
    public boolean removeImageById(final String imageId) throws ShiftClientException {
        final HttpDelete delete = new HttpDelete(this.imageUrl + imageId);
        final HttpResponse response = executeRequest(delete);

        return response.getStatusLine().getStatusCode() == 204;
    }

}