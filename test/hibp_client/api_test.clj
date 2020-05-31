(ns hibp-client.api-test
  (:require [clojure.test :refer :all]
            [hibp-client.api :refer :all]))

(def user-agent-header "user-agent")

(deftest default-headers-test

  (testing "given no headers"
    (testing "specifies user agent"
      (let [headers (default-headers nil)
            expected-user-agent "hibp-client"]
        (is (contains? headers user-agent-header))
        (is (= (headers user-agent-header) expected-user-agent)))))

  (testing "given user agent header"
    (testing "overrides user-agent header"
      (let [user-agent "test user agent"
            headers (default-headers {user-agent-header user-agent})]
        (is (= (headers user-agent-header) user-agent)))))

  (testing "given custom headers"
    (testing "merges custom headers to default headers"
      (let [custom-header "test-header"
            custom-header-value "test header value"
            custom-headers {custom-header custom-header-value}
            headers (default-headers custom-headers)]
        (is (= (headers custom-header) custom-header-value))))))