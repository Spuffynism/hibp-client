(ns hibp-client.integration.breach-test
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/mock-http-client)

(defn in-seq? ([item seq] (some #(= item %) seq)))

(deftest ^:integration get-for-account-test
  (let [api-key "api-key"
        account "test@example.org"]
    (testing "gets breaches for account without filter"
      (doseq [breach ["Verified breach" "Unverified breach"]]
        (is (in-seq? {:name breach} (get-for-account api-key account)))))

    (testing "gets only verified breaches when filtering"
      (let [result (get-for-account api-key account {:include-unverified false})]
        (is (in-seq? {:name "Verified breach"} result))
        (is (not (in-seq? {:name "Unverified breach"} result)))))

    (testing "gets full breach when fetching non-truncated breach"
      (let [breach (first (get-for-account api-key account {:truncate-response false}))]
        (are [property value]
          (= (property breach) value)
          :description "Breach description"
          :is-fabricated false
          :logo-path "https://example.org/logo.png"
          :name "Adobe"
          :is-spam-list false
          :added-date "2013-12-04T00:00:00Z"
          :is-verified true
          :is-retired false
          :title "Adobe"
          :pwn-count 152445165
          :breach-date "2013-10-04"
          :domain "adobe.com"
          :modified-date "2013-12-04T00:00:00Z"
          :is-sensitive false
          :data-classes ["Email addresses" "Password hints" "Passwords" "Usernames"])))))

