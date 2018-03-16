(ns hibp-client.all-test
  (:require [clojure.test :refer :all]
            [hibp-client.api-test :as api-test]
            [hibp-client.breach-test :as breach-test]
            [hibp-client.paste-test :as paste-test]
            [hibp-client.pwned-passwords-test :as pwned-passwords-test]))

(deftest a-test
    (is (= 1 1)))
