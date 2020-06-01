(ns hibp-client.breach-test
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.api :as api]))

(deftest get-for-account-test
  (testing "uses renamed query params"
    (with-redefs [api/get-json-body
                  (fn [_ {:keys [query-params]}]
                    (is (contains? query-params :truncateResponse)))]
      (get-for-account "api-key" "test@example.com" {:truncate-response true})))

  (testing "uses api key"
    (with-redefs [api/get-json-body
                  (fn [_ {:keys [api-key]}]
                    (is (= api-key "api-key")))]
      (get-for-account "api-key" "test@example.com")))

  (testing "builds account path"
    (with-redefs [api/get-json-body
                  (fn [path _]
                    (is path "/breachedaccount/test@example.com"))]
      (get-for-account "api-key" "test@example.com"))))

(deftest get-all-test
  (testing "gets breaches path")
  (testing "specifies domain in query params if present"))

(deftest get-for-name-test
  (testing "checks that name is present")
  (testing "builds breach path"))

(deftest get-data-classes-test
  (testing "gets dataclasses path"))