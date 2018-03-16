(ns hibp-client.api
    (:require [clj-http.client :as http]
        [camel-snake-kebab.core :refer [->kebab-case-keyword]]))

(def hibp-api-url "https://haveibeenpwned.com/api/v2/")

(defmethod http/coerce-response-body :json-kebab-keys [req resp]
  (http/coerce-json-body req resp (memoize ->kebab-case-keyword) false))

(defn get-body
  "Retrieves the body of a GET request made to hibp's API"
  ([path]
    (get-body path nil))
  ([path query-params]
    (try
      (:body
        (http/get (str hibp-api-url path) {
          :accept :json
          :as :json-kebab-keys
          :query-params query-params}))
      (catch Exception e nil))))

(defn exists?
  "Indicates if a resource exists by checking the http status code"
  ([path form-params]
    (exists? path form-params nil))
  ([path form-params query-params]
    (try
      (let [status
        (:status (http/post (str hibp-api-url path) {
          :content-type :x-www-form-urlencoded
          :query-params query-params
          :form-params form-params}))]
        (= status 200))
      (catch Exception e false))))