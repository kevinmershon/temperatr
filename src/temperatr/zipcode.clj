(ns temperatr.zipcode
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn- get-location
  "Use Google Geocoding API to get the lat/long location of a zipcode"
  [zipcode]

  (println (format "searching for zipcode %d..." zipcode))

  ; hit Google Maps API for the zipcode
  (let [response
    (try
      (client/get (format "http://maps.googleapis.com/maps/api/geocode/json?components=postal_code:%d&sensor=false"
                          zipcode))
    (catch Exception e
      (println (.getMessage e))
      (throw (Exception. "request failed!"))))]

    ; check for HTTP 200 (success)
    (if (not= (:status response) 200)
      (throw (Exception. "request failed!")))

    ; parse the response into JSON
    ; TODO -- add error handling
    (json/read-str (:body response))))

(defn exists?
  "Use Google Geocoding API to check if a zipcode exists"
  [zipcode]

  (let [response-json (get-location zipcode)]
    ; check the status
    ; either "OK" or "ZERO_RESULTS"
    (= (get response-json "status") "OK")))
