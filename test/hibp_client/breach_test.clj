(ns hibp-client.breach-test
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.test-fixtures :as test-fixtures]))

(use-fixtures :once test-fixtures/make-api-passthrough)

(deftest get-for-account-test
  (testing "uses renamed query params"
    (let [{:keys [configuration]}
          (get-for-account "api-key" "test@example.com" {:truncate-response true})]
      (is (contains? (:query-params configuration) :truncateResponse))))

  (testing "uses api key"
    (let [{:keys [configuration]} (get-for-account "api-key" "test@example.com")]
      (is (= (:api-key configuration) "api-key"))))

  (testing "builds account path"
    (let [{:keys [path]} (get-for-account "api-key" "test@example.com")]
      (is (= path "/breachedaccount/test@example.com")))))

(deftest get-all-test
  (testing "gets breaches path"
    (is (= (:path (get-all)) "/breaches")))

  (testing "params are not present if domain is absent"
    (is (nil? (:configuration (get-all)))))

  (testing "specifies domain in query params if present"
    (let [{:keys [configuration]} (get-all "example.org")]
      (is (= (:query-params configuration) {:domain "example.org"})))))

(deftest get-for-name-test
  (testing "checks that name is present"
    (is (thrown? AssertionError (get-for-name nil))))

  (testing "builds breach path"
    (is (= (:path (get-for-name "breach-name")) "/breach/breach-name"))))

(deftest get-data-classes-test
  (testing "gets dataclasses path"
    (is (= (:path (get-data-classes)) "/dataclasses"))))