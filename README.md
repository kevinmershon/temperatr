temperatr
=========

A silly project in Clojure to search for nearby locales with more favorable weather.

Goals of the project
--------------------

Provide a web interface which:
* provides inputs for a user to
  * specify a zip code
  * specify a distance in miles
  * specify a desired temperate range (e.g. 70 to 85 degrees)
* asynchronously searches and displays a list of locations which match the specified criteria
* performs searches concurrently
* automatically updates in-place
* handles connectivity errors, timeouts, long-running queries, etc.
* provides server-side functionality for
  * full-text searching
  * sorting
  * etc.

![no idea what I'm doing](http://i2.kym-cdn.com/photos/images/original/000/234/739/fa5.jpg)
