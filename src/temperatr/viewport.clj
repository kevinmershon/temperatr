(ns temperatr.viewport)

(defn get-bounds
  "Get the bounding viewport (northwest lat/long, southeast lat/long) for the
  specified point and maximum distance in miles"
  [latitude longitude max-distance-in-miles]
  (println
    (format "calculating bounds for within %d miles of [%.4f,%.4f]"
      max-distance-in-miles
      latitude
      longitude))

  {
    :north-west {:latitude 0.0, :longitude 0.0}
    :south-east {:latitude 0.0, :longitude 0.0}
  }
)
