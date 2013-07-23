(ns temperatr.zipcode-test
  (:require [clojure.test :refer :all]
            [temperatr.zipcode :refer :all]))

(deftest exists-invalid-test
  (testing "Test an invalid zipcode"
    (is (false? (temperatr.zipcode/exists? 123456789)))))

(deftest exists-valid-test
  (testing "Test a valid zipcode"
    (is (true? (temperatr.zipcode/exists? 90210)))))
