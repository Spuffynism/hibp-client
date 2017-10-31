(ns hibp-client.breach
  (:require [hibp-client.api :as api]))

(defn get-for-account 
  ":domain, :truncate-response and :include-unverified can be used to filter 
  the response"
  ([account]
    (api/get-body (str "breachedaccount/" account)))
  ([account filter] 
    (api/get-body (str "breachedaccount/" account) 
      (set/rename-keys filter {:truncate-response :truncateResponse
        :include-unverified :includeUnverified}))))

(defn get-all
  ([] 
    (api/get-body "breaches"))
  ([domain]
    {:pre [(not (nil? domain))]}
    (api/get-body "breaches" {:domain domain})))

(defn get-for-name
  [name]
    {:pre [(not (nil? name))]} 
    (api/get-body (str "breach/" name)))

(defn get-data-classes
  [] 
    (api/get-body "dataclasses"))