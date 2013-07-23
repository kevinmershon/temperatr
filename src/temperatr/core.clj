(ns temperatr.core
  (:require [temperatr.zipcode :as zipcode]))

(defn -main
  "Start temperatr"
  []
  (println (if (true? (zipcode/search 93312))
               "exists"
               "doesn't exist")))
