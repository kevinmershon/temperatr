(ns temperatr.viewport-test
  (:require [clojure.test :refer :all]
            [temperatr.viewport :refer :all]))

(deftest get-point-at-bearing-allballs-test
  (testing "Test get-point-at-bearing with allballs"
    (let [destination (#'temperatr.viewport/get-point-at-bearing 0.0 0.0 0.0 0)]
      (is (= 0.0 (:latitude destination)))
      (is (= 0.0 (:longitude destination))))))

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
