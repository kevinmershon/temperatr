(ns temperatr.core
  (:require [temperatr.geocode :as geocode]))

(defn -main
  "Start temperatr"
  []
  (println (if (true? (#'geocode/zipcode-exists? 93312))
               "exists"
               "doesn't exist")))
