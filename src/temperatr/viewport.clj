(ns temperatr.viewport)

(defn deg-to-rad
  "Convert degrees to radians"
  [degrees]

  (* degrees (/ Math/PI 180.0)))

(defn rad-to-deg
  "Convert radians to degrees"
  [radians]

  (/ radians (/ Math/PI 180.0)))

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

  (let [radius-of-earth 3959 ; in miles
        angular-distance (/ distance-in-miles radius-of-earth)
        lat-rad (deg-to-rad latitude)
        lon-rad (deg-to-rad longitude)
        bearing-rad (deg-to-rad bearing)
        destination-latitude (Math/asin (+ (* (Math/sin lat-rad)
                                              (Math/cos angular-distance))
                                           (*
                                              (Math/cos lat-rad)
                                              (Math/sin angular-distance)
                                              (Math/cos bearing-rad))))]

      { :latitude (rad-to-deg destination-latitude)
        :longitude (rad-to-deg (+ lon-rad
                                  (Math/atan2 (* (Math/sin bearing-rad)
                                                 (Math/sin angular-distance)
                                                 (Math/cos lat-rad))
                                              (- (Math/cos angular-distance)
                                                 (* (Math/sin lat-rad)
                                                    (Math/sin destination-latitude))))))}))

(defn get-bounds
  "Get the bounding viewport (northwest lat/long, southeast lat/long) for the
  specified point and maximum distance in miles"
  [latitude longitude distance-in-miles]
  (println
    (format "calculating bounds for within %.2f miles of [%.4f,%.4f]"
      distance-in-miles
      latitude
      longitude))

  (let [north (get-point-at-bearing latitude longitude   0.0 distance-in-miles)
        east  (get-point-at-bearing latitude longitude  90.0 distance-in-miles)
        south (get-point-at-bearing latitude longitude 180.0 distance-in-miles)
        west  (get-point-at-bearing latitude longitude 270.0 distance-in-miles)]

    { :north-west {:latitude (:latitude north), :longitude (:longitude west)}
      :south-east {:latitude (:latitude south), :longitude (:longitude east)}}))
