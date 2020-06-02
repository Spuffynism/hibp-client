(ns hibp-client.paste-test
  (:require [clojure.test :refer :all]
            [hibp-client.paste :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/make-api-passthrough)

(deftest get-for-account-test
  (testing "checks that account is specified"
    (is (thrown? AssertionError (get-for-account "api-key" nil))))

  (testing "builds path from account"
    (is (=
          (:path (get-for-account "api-key" "test@example.org"))
          "/pasteaccount/test@example.org")))

  (testing "uses api key"
    (is (=
          (:configuration (get-for-account "api-key" "test@example.org"))
          {:api-key "api-key"}))))