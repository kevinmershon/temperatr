(ns temperatr.core
  (:require [temperatr.zipcode :as zipcode]))

(defn -main
  "Start temperatr"
  []
  (zipcode/search 93312))
