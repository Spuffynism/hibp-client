(ns hibp-client.test-fixtures
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.api :as api]
            [clj-http.client :as http]))

(defn- passthrough-get-json-body
  "Returns the given arguments as a hash"
  ([path] {:path path})
  ([path configuration] {:path path :configuration configuration}))

(defn- passthrough-get-body
  "Returns the given arguments as a hash"
  ([path configuration] {:path path :configuration configuration})
  ([path configuration extra-configuration] {:path path
                                             :configuration configuration
                                             :extra-configuration extra-configuration}))

(defn make-api-passthrough
  "Makes the api passthrough"
  [f]
  (with-redefs [api/get-json-body passthrough-get-json-body
                api/get-body passthrough-get-body]
    (f)))

(def breaches
  {:non-truncated {:description "Breach description"
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
                   :data-classes ["Email addresses" "Password hints" "Passwords" "Usernames"]}
   :unverified {:name "Unverified breach"}
   :verified {:name "Verified breach"}})

(defn mock-get
  "Mocks a http get request"
  [url & [req & r]]
  (cond
    (= url "https://haveibeenpwned.com/api/v3/breachedaccount/test@example.org")
    (cond
      (false? (:includeUnverified (:query-params req))) {:body (lazy-seq [(:verified breaches)])}
      (false? (:truncateResponse (:query-params req))) {:body (lazy-seq [(:non-truncated breaches)])}
      :else {:body (lazy-seq [(:verified breaches) (:unverified breaches)])})
    (= url "https://haveibeenpwned.com/api/v3/breaches") {:body nil}
    (= url "https://haveibeenpwned.com/api/v3/breach/breach-name")
    {:body (lazy-seq [(:non-truncated breaches)])}
    (= url "https://haveibeenpwned.com/api/v3/dataclasses") {:body nil}
    (= url "https://haveibeenpwned.com/api/v3/pasteaccount/test@example.org") {:body nil}
    (= url "https://api.pwnedpasswords.com/range/ABCDE")
    {:body (str "0018A45C4D1DEF81644B54AB7F969B88D65:1"
                "00D4F6E8FA6EECAD2A3AA415EEC418D38EC:2"
                "011053FD0102E94D6AE2F8B83D76FAF94F6:1"
                "012A7CA357541F0AC487871FEEC1891C49C:2"
                "0136E006E24E7D152139815FB0FC6A50B15:2")}
    :else (throw (Exception. " Undefined mocked api behavior "))))

(defn mock-http-client
  " Mocks the http client "
  [f]
  (with-redefs [http/get mock-get]
    (f)))