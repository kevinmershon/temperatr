(ns temperatr.geocode-test
  (:require [clojure.test :refer :all]
            [temperatr.geocode :refer :all]))

(deftest exists-invalid-test
  (testing "Test an invalid geocode"
    (is (false? (temperatr.geocode/zipcode-exists? 123456789)))))

(deftest exists-valid-test
  (testing "Test a valid zipcode"
    (is (true? (temperatr.geocode/zipcode-exists? 90210)))))
