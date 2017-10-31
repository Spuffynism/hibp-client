(ns hibp-clj-client.xyz.ndlr.api
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
    (println "query params were: " query-params)
    (try
    (:body
      (http/get (str url path) {:accept :json 
        :as :json-kebab-keys 
        :query-params query-params}))
    (catch Exception e nil))))

(defn exists? 
  "check if a resource exists by checking the http status code"
  [path query-params]
  (http/get (str url path) {:accept :json 
    :as :json-kebab-keys 
    :query-params query-params}))