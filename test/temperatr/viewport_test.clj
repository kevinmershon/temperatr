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
      (is (= "0.00000000" (format "%.8f" (:longitude south))))

      ; east and west should have zero latitudinal component
      (is (= "0.00000000" (format "%.8f" (:latitude east))))
      (is (= "-0.00000000" (format "%.8f" (:latitude west))))

      ; magic number for 2.0 miles is 0.0289 degrees (at (0,0))
      (is (= "0.0289" (format "%.4f" (:latitude north))))
      (is (= "-0.0289" (format "%.4f" (:latitude south))))
      (is (= "0.0289" (format "%.4f" (:longitude east))))
      (is (= "-0.0289" (format "%.4f" (:longitude west))))
    )))

(deftest get-bounds-allballs-test
  (testing "Test get-bounds with allballs"
    (let [bounds (temperatr.viewport/get-bounds 0.0 0.0 0.0)]
      ; neither north-east nor south-west are nil
      (is (not (nil? (:north-east bounds))))
      (is (not (nil? (:south-west bounds))))

      ; all 4 coordinates are 0.0
      (is (= (:latitude (:north-east bounds)) 0.0))
      (is (= (:longitude (:north-east bounds)) 0.0))
      (is (= (:latitude (:south-west bounds)) 0.0))
      (is (= (:longitude (:south-west bounds)) 0.0)))))

(deftest get-bounds-at-2-miles-test
  (testing "Test get-bounds at origin (0,0) with 2 mile distance"
    (let [bounds (temperatr.viewport/get-bounds 0.0 0.0 2.0)]
      ; neither north-east nor south-west are nil
      (is (not (nil? (:north-east bounds))))
      (is (not (nil? (:latitude (:north-east bounds)))))
      (is (not (nil? (:longitude (:north-east bounds)))))
      (is (not (nil? (:south-west bounds))))
      (is (not (nil? (:latitude (:south-west bounds)))))
      (is (not (nil? (:longitude (:south-west bounds)))))

      ; magic number for 2.0 miles is 0.0289 degrees (at (0,0))
      (is (= "0.0289" (format "%.4f" (:latitude (:north-east bounds)))))
      (is (= "0.0289" (format "%.4f" (:longitude (:north-east bounds)))))
      (is (= "-0.0289" (format "%.4f" (:latitude (:south-west bounds)))))
      (is (= "-0.0289" (format "%.4f" (:longitude (:south-west bounds)))))
    )))
