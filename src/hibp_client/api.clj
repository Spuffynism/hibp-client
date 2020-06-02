(ns hibp-client.api
  (:require [clj-http.client :as http]
            [camel-snake-kebab.core :refer [->kebab-case-keyword]]))

(def hibp-api-url "https://haveibeenpwned.com/api/v3")

(defn default-headers
  ([headers] (merge {"user-agent" "hibp-client"} headers)))

(defmethod http/coerce-response-body :json-kebab-keys [req resp]
  (http/coerce-json-body req resp (memoize ->kebab-case-keyword) false))

(defn get-body
  ([path configuration]
   (get-body path configuration nil))
  ([path {:keys [complete-path api-key query-params]} configuration]
   (try
     (:body (http/get
              (or complete-path (str hibp-api-url path))
              (merge {:headers (default-headers (if api-key {"hibp-api-key" api-key}))
                      :query-params query-params}
                     configuration)))
     (catch Exception e
       (str "Caught exception: " (.getMessage e))))))

(defn get-json-body
  "Retrieves the body of a json GET request made to hibp's API"
  ([path]
   (get-json-body path nil))
  ([path configuration]
   (get-body path configuration {:accept :json :as :json-kebab-keys})))