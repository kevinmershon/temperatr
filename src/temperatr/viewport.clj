(ns temperatr.viewport)

(defn- get-point-at-bearing
  "Get the lat/long point at the specified distance in miles, along the
  specified bearing"
  [latitude longitude bearing distance-in-miles]

  ;in java:
  ;
  ;var lat2 = Math.asin( Math.sin(lat1)*Math.cos(d/R) +
  ;           Math.cos(lat1)*Math.sin(d/R)*Math.cos(brng) );
  ;var lon2 = lon1 + Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(lat1),
  ;                  Math.cos(d/R)-Math.sin(lat1)*Math.sin(lat2));

  (let [radius-of-earth 3959] ; in miles
    (let [destination-latitude
      (+
        (Math/asin
          (*
            (Math/sin latitude)
            (Math/cos (/ distance-in-miles radius-of-earth))
          )
        )
        (*
          (Math/cos latitude)
          (Math/sin (/ distance-in-miles radius-of-earth))
          (Math/cos bearing)
        )
      )]

      { :latitude destination-latitude
        :longitude
          (+
            longitude
            (Math/atan2
              (*
                (Math/sin bearing)
                (Math/sin (/ distance-in-miles radius-of-earth))
                (Math/cos latitude)
              )
              (-
                (Math/cos (/ distance-in-miles radius-of-earth))
                (*
                  (Math/sin latitude)
                  (Math/sin destination-latitude)
                )
              )
            )
          )
      }
    )
  )
)

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
