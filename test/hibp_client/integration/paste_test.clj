(ns hibp-client.integration.paste-test
  (:require [clojure.test :refer :all]
            [hibp-client.paste :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/mock-http-client)

(deftest ^:integration get-for-account-test
  (testing "gets for account"
    (let [api-key "api key"
          account "test@example.org"
          paste (first (get-for-account api-key account))]
      (are [property value]
        (= (property paste) value)
        :source "source"
        :id "id"
        :title "title"
        :date "2013-12-04T00:00:00Z"
        :email-count 10))))