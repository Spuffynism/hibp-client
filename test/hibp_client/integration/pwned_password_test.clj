(ns hibp-client.integration.pwned-password-test
  (:require [clojure.test :refer :all]
            [hibp-client.pwned-password :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/mock-http-client)

(deftest ^:integration list-for-range-test
  (testing "lists for range"
    (is (= (list-for-range "ABCDE")
           (str "0018A45C4D1DEF81644B54AB7F969B88D65:1"
                "00D4F6E8FA6EECAD2A3AA415EEC418D38EC:2"
                "011053FD0102E94D6AE2F8B83D76FAF94F6:1"
                "012A7CA357541F0AC487871FEEC1891C49C:2"
                "0136E006E24E7D152139815FB0FC6A50B15:2")))))