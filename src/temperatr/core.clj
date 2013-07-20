(ns temperatr.core)

(defn -main
  "Start temperatr"
  []
  (zipcode-search 93312))

(defn zipcode-search
  [zipcode]
  (println
    (format "searching for zipcode %d..." zipcode)))
