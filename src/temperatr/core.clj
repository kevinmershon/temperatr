(ns temperatr.core)

(defn -main
  "Start temperatr"
  []
  (zipcode-search 93312))

(defn zipcode-search
  [zipcode]
  (println
    (format "searching for zipcode %d..." zipcode))

  ; get an HTTP client
  (require '[clj-http.client :as client])

  ; hit Google Maps API for the zipcode
  (def response
    (client/get
      (format
        "http://maps.googleapis.com/maps/api/geocode/json?address=%d&sensor=false"
        zipcode)))

  ; print the response JSON for now
  (println response)
)
