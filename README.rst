Java Client for the Shift Import API
====================================

A simple client for the Shift import API that allows for easy integration in
any Java based CMS.


Initialization
--------------

Initialize the Client::

    // obtained through Shift Dashboard
    final String APIKEY = "test";
    // obtained through Shift Dashboard
    final String APISECRET = "test";
    // use HTTP or HTTPS
    final boolean secure = false;

    final ShiftClient shift = new ShiftClient(APIKEY, APISECRET, secure);

Create a new article::

    final Article article = new Article();
    article.setTitle("Ein Titel");
    article.setTeaser("Ein Teaser");
    article.setHtml("<p>Der lange Text f&uumlr; den HTML Body des " + 
        " Artikels</p>");
    article.setTimestamp(new DateTime(2013, 6, 24, 6, 13));
    article.setUrl("http://www.meineurl.de/article/123");
    article.setRessort(Arrays.asList("Politik"));
    article.setSource(Arrays.asList("ich"));


Add an image to the article::

    final ArticleImage image = ArticleImage();
    image.setCaption("Ein Bild");
    image.setCopyright("Fotograf XY/Bildfirma");
    image.setSource(Arrays.asList("ich"));
    article.setImage(image);

Insert the article::

    boolean successful = client.addArticle(article);

Remove the article::

    boolean successful = client.removeArticle(article);



Images
------

By default any image that is added along an article is also added as a
*standalone* image, i.e. it can be used in image gallery widgets. If you want
to prevent that, add the line::

    image.setAsSeparatImage(false);

to the example above.

Indexing standalone images in higher resolution, e.g., is straight forward::

Create the image::

    final Image image = new Image();
    image.setCaption("Ein Bild");
    image.setUrl("http://www.meineurl.de/static/img/123234123.jpg");
    image.setTimestamp(new DateTime(2013, 6, 24, 6, 13));
    image.setSource(Arrays.asList("ich"));

Adding the image::

    boolean successful = client.addImage(image);

Removing it again::

    boolean successful = client.removeImage(image);


Errors
------

There are two possible sources of exceptions being raised: before sending the
articles and images across the wire they are being validated. If there is any
problem with the data, you will get a `ShiftDataViolation` exception. If there
was a problem sending the data to the Shift servers, you will get a
`ShiftClientException`::

    try {
        client.addImage(image);
    catch(final ShiftDataViolation e) {
        // this would print something like:
        // url cannot be null
        System.out.println(e.getMessage());
    } catch(final ShiftClientException e) {
        /**
         * `e` includes the cause for the exception. This can be any problems
         * related to the OAuth settings or any `IOException` raised while
         * communicating with Shift.
         *
         */
    }


LICENSE
=======

Apache 2.0 (See LICENSE file)
