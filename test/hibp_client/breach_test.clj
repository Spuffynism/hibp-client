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
                  (fn [path]
                    (is path "/breachedaccount/test@example.com"))]
      (get-for-account "api-key" "test@example.com"))))

(deftest get-all-test
  (testing "gets breaches path"
    (with-redefs [api/get-json-body
                  (fn [path]
                    (is (= path "/breaches")))]
      (get-all)))

  (testing "params are not present if domain is absent"
    (with-redefs [api/get-json-body
                  (fn [_ params]
                    (is (nil? params)))]
      (get-all)))

  (testing "specifies domain in query params if present"
    (with-redefs [api/get-json-body
                  (fn [_ {:keys [query-params]}]
                    (is (= query-params {:domain "example.org"})))]
      (get-all "example.org"))))

(deftest get-for-name-test
  (testing "checks that name is present"
    (is (thrown? AssertionError (get-for-name nil))))

  (testing "builds breach path"
    (with-redefs [api/get-json-body
                  (fn [path]
                    (is path "/breach/breach-name"))]
      (get-for-name "breach-name"))))

(deftest get-data-classes-test
  (testing "gets dataclasses path"
    (with-redefs [api/get-json-body
                  (fn [path]
                    (is path "/dataclasses"))]
      (get-data-classes))))