(ns hibp-client.api
  (:require [clj-http.client :as http]
            [camel-snake-kebab.core :refer [->kebab-case-keyword]]))

(def hibp-api-url "https://haveibeenpwned.com/api/v3")

(defn default-headers
  ([headers] (merge {"user-agent" "hibp-client"} headers)))

(defmethod http/coerce-response-body :json-kebab-keys [req resp]
  (http/coerce-json-body req resp (memoize ->kebab-case-keyword) false))

(defn get-json-body
  "Retrieves the body of a json GET request made to hibp's API"
  ([path]
   (get-json-body path nil))
  ([path {:keys [complete-path api-key query-params]}]
   (try
     (->
       (http/get
         (or complete-path (str hibp-api-url path))
         {:accept :json
          :headers (default-headers (if api-key {"hibp-api-key" api-key}))
          :as :json-kebab-keys
          :query-params query-params})
       :body)
     (catch Exception e
       (str "Caught exception: " (.getMessage e))))))

(defn get-body
  "Retrieves the body of a json GET request made to hibp's API"
  ([path]
   (get-body path nil))
  ([path {:keys [complete-path api-key query-params]}]
   (try
     (->
       (http/get
         (or complete-path (str hibp-api-url path))
         {:headers (default-headers (if api-key {"hibp-api-key" api-key}))
          :query-params query-params})
       :body)
     (catch Exception e
       (str "Caught exception: " (.getMessage e))))))

(defn exists?
  "Indicates if a resource exists by checking the http status code"
  ([path {:keys [query-params form-params]}]
   (try
     (->
       (http/post
         (str hibp-api-url path)
         {:content-type :x-www-form-urlencoded
          :headers (default-headers {})
          :query-params query-params
          :form-params form-params})
       :status
       (= 200))
     (catch Exception _ false))))