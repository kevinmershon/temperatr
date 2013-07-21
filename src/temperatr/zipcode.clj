(ns temperatr.zipcode
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn search
  [zipcode]
  (println
    (format "searching for zipcode %d..." zipcode))

  ; hit Google Maps API for the zipcode
  (try
    (def response
      (client/get
        (format
          "http://maps.googleapis.com/maps/api/geocode/json?components=postal_code:%d&sensor=false"
          zipcode)))
    (catch Exception e
      (
        (println (.getMessage e))
        (throw (Exception. "request failed!"))
      )
    )
  )

  ; check for HTTP 200 (success)
  (if (not= (:status response) 200)
    (throw (Exception. "request failed!")))

  ; parse the response into JSON
  ; TODO -- add error handling
  (def responseJSON
    (json/read-str (:body response)))

  ; check the status
  ; either "OK" or "ZERO_RESULTS"
  (= (get responseJSON "status") "OK")
)
