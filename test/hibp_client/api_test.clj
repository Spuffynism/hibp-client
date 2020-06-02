(ns hibp-client.api-test
  (:require [clojure.test :refer :all]
            [hibp-client.api :refer :all]
            [clj-http.client :as http]))

(deftest default-headers-test
  (let [user-agent-header "user-agent"]
    (testing "given no headers, specifies default user agent"
      (let [headers (default-headers nil)
            expected-user-agent "hibp-client"]
        (is (contains? headers user-agent-header))
        (is (= (headers user-agent-header) expected-user-agent))))

    (testing "given user agent header, overrides user-agent header"
      (let [user-agent "test user agent"
            headers (default-headers {user-agent-header user-agent})]
        (is (= (headers user-agent-header) user-agent))))

    (testing "given custom headers, merges custom headers to default headers"
      (let [custom-header "test-header"
            custom-header-value "test header value"
            custom-headers {custom-header custom-header-value}
            headers (default-headers custom-headers)]
        (is (= (headers custom-header) custom-header-value))))))

;; Helper function to test http/get's path
(defn map-path-to-body
  [path _]
  {:body path})

;; Helper function to test http/get's configuration
(defn map-configuration-to-body
  [_ configuration]
  {:body configuration})

(deftest get-body-test

  (with-redefs [http/get map-path-to-body]
    (testing "complete path is built from path and api url"
      (is (= (get-body "/test" nil) (str hibp-api-url "/test"))))

    (testing "overrides path with complete path"
      (is (= (get-body "overriden_path" {:complete-path "complete_path"})
             "complete_path"))))

  (with-redefs [http/get map-configuration-to-body]
    (testing "uses api-key if provided"
      (let [api-key "api key"]
        (is (= ((:headers (get-body "" {:api-key api-key})) "hibp-api-key")
               api-key))))

    (testing "uses query params"
      (let [query-params {:param "value"}]
        (is (= (:query-params (get-body "" {:query-params query-params}))
               query-params))))

    (testing "merges extra configuration"
      (let [extra-configuration {:parameter "configuration parameter value"}]
        (is (= (:parameter (get-body "" nil extra-configuration))
               "configuration parameter value")))))

  (testing "catches http exception"
    (let [exception-message "exception message"]
      (with-redefs [http/get (fn [_ _] (throw (Exception. exception-message)))]
        (is (= (get-body "" nil) (str "Caught exception: " exception-message)))))))

(deftest get-json-body-test
  (testing "handles json"
    (with-redefs [http/get map-configuration-to-body]
      (let [{accept :accept as :as} (get-json-body nil)]
        (is (= accept :json))
        (is (= as :json-kebab-keys))))))
