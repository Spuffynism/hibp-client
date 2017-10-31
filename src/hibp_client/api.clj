(ns hibp-client.api
    (:require [clj-http.client :as http]
        [camel-snake-kebab.core :refer [->kebab-case-keyword]]))

(def url "https://haveibeenpwned.com/api/v2/")

(defmethod http/coerce-response-body :json-kebab-keys [req resp]
  (http/coerce-json-body req resp (memoize ->kebab-case-keyword) false))

(defn get-body
  "makes a GET request to hibp's API"
  ([path]
    (get-body path nil))
  ([path query-params]
    (try
      (:body
        (http/get (str url path) {:accept :json 
          :as :json-kebab-keys 
          :query-params query-params}))
      (catch Exception e nil))))

(defn exists? 
  "indicates if a resource exists by checking the http status code"
  ([path form-params]
    (exists? path form-params nil))
  ([path form-params query-params]
    (try 
      (def status 
        (:status (http/post (str url path) {
          :content-type :x-www-form-urlencoded
          :query-params query-params  
          :form-params form-params})))
      (= status 200)
      (catch Exception e false))))