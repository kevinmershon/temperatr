(ns temperatr.viewport-test
  (:require [clojure.test :refer :all]
            [temperatr.viewport :refer :all]))

(deftest get-point-at-bearing-allballs-test
  (testing "Test get-point-at-bearing with allballs"
    (let [destination (#'temperatr.viewport/get-point-at-bearing 0.0 0.0 0.0 0)]
      (is (= 0.0 (:latitude destination)))
      (is (= 0.0 (:longitude destination))))))

(deftest get-point-at-bearing-corners-test
  (testing "Test get-point-at-bearing with all four corners"
    (let [north (#'temperatr.viewport/get-point-at-bearing 0.0 0.0   0.0 2.0)
          east  (#'temperatr.viewport/get-point-at-bearing 0.0 0.0  90.0 2.0)
          south (#'temperatr.viewport/get-point-at-bearing 0.0 0.0 180.0 2.0)
          west  (#'temperatr.viewport/get-point-at-bearing 0.0 0.0 270.0 2.0)]

      ; north and south should have zero longitudinal component
      (is (= "0.00000000" (format "%.8f" (:longitude north))))
      (is (= "0.0289" (format "%.4f" (:latitude north))))
      (is (= "0.00000000" (format "%.8f" (:longitude south))))
      (is (= "-0.0289" (format "%.4f" (:latitude south))))

      ; east and west should have zero latitudinal component
      (is (= "0.00000000" (format "%.8f" (:latitude east))))
      (is (= "0.0289" (format "%.4f" (:longitude east))))
      (is (= "-0.00000000" (format "%.8f" (:latitude west))))
      (is (= "-0.0289" (format "%.4f" (:longitude west))))

      ; magic number for 2.0 miles is 0.0289 degrees (at (0,0))
    )))

(deftest get-bounds-allballs-test
  (testing "Test get-bounds with allballs"
    (let [bounds (temperatr.viewport/get-bounds 0.0 0.0 0)]
      ; neither north-west nor south-east are nil
      (is (not (nil? (:north-west bounds))))
      (is (not (nil? (:south-east bounds))))

      ; all 4 coordinates are 0.0
      (is (= (:latitude (:north-west bounds)) 0.0))
      (is (= (:longitude (:north-west bounds)) 0.0))
      (is (= (:latitude (:south-east bounds)) 0.0))
      (is (= (:longitude (:south-east bounds)) 0.0)))))
