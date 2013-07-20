(ns temperatr.core
  (:require [clj-http.client :as client]))

(defn zipcode-search
  [zipcode]
  (println
    (format "searching for zipcode %d..." zipcode))

  ; hit Google Maps API for the zipcode
  (def response
    (client/get
      (format
        "http://maps.googleapis.com/maps/api/geocode/json?address=%d&sensor=false"
        zipcode)))

  ; print the response JSON for now
  (println response)
)

(defn -main
  "Start temperatr"
  []
  (zipcode-search 93312))
