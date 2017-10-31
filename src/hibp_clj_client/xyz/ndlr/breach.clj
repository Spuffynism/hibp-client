(ns hibp-clj-client.xyz.ndlr.breach
  (:require [hibp-clj-client.xyz.ndlr.api :as api]
    [clojure.set :as set]))

(defn get-for-account 
  "can use :domain, :truncate-response and :include-unverified to filter"
  ([account]
    (api/get-body (str "breachedaccount/" account)))
  ([account filter] 
    (api/get-body (str "breachedaccount/" account) 
      (set/rename-keys filter {:truncate-response :truncateResponse
        :include-unverified :includeUnverified}))))

(defn get-all 
  "domain is optional"
  ([] 
    (get-all nil))
  ([domain] 
    (api/get-body "breaches/" {:domain domain})))

(defn get-for-site
  [site]
  {:pre [(not (nil? site))]} 
  (api/get-body (str "breach/" site)))

(defn get-data-classes
  [] (do))