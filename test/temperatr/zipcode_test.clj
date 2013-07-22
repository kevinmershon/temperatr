(ns temperatr.zipcode-test
  (:require [clojure.test :refer :all]
            [temperatr.zipcode :refer :all]))

(deftest zipcode-invalid-test
  (testing "Test an invalid zipcode"
    (is (false? (temperatr.zipcode/search 123456789)))))

(deftest zipcode-valid-test
  (testing "Test a valid zipcode"
    (is (true? (temperatr.zipcode/search 90210)))))
