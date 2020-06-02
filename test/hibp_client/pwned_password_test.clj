(ns hibp-client.pwned-password-test
  (:require [clojure.test :refer :all]
            [hibp-client.pwned-password :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/make-api-passthrough)

(def valid-hash "ABCDE")

(deftest list-for-range-test
  (testing "checks that hash is not too long"
    (is (thrown? AssertionError (list-for-range "too long hash"))))

  (testing "checks that hash is not too short"
    (let [too-short-hash "abc"]
      (is (thrown? AssertionError (list-for-range too-short-hash)))))

  (testing "doesn't use a partial path"
    (is (nil? (:path (list-for-range valid-hash)))))

  (testing "specifies complete path"
    (let [{:keys [configuration]} (list-for-range valid-hash)]
      (is (= (:complete-path configuration)
             (str "https://api.pwnedpasswords.com/range/" "ABCDE")))))

  (testing "sets padding header if desired"
    (let [{:keys [configuration]} (list-for-range valid-hash true)]
      (is (= (:headers configuration) {"add-padding" "true"})))))