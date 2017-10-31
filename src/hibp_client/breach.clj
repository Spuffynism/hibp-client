(ns hibp-client.breach
  (:require [hibp-client.api :as api]
    [clojure.set :as set]))

(defn get-for-account 
  "Gets breaches for a specific account. :domain, :truncate-response and 
  :include-unverified can be specified to filter the response"
  {:see "https://haveibeenpwned.com/api/v2/#BreachesForAccount"}
  ([account]
    (api/get-body (str "breachedaccount/" account)))
  ([account filter] 
    (api/get-body (str "breachedaccount/" account) 
      (set/rename-keys filter {:truncate-response :truncateResponse
        :include-unverified :includeUnverified}))))

(defn get-all
  "Gets all breaches. A domain name can be specified to filter the response"
  {:see "https://haveibeenpwned.com/api/v2/#AllBreaches"}
  ([] 
    (api/get-body "breaches"))
  ([domain]
    {:pre [(not (nil? domain))]}
    (api/get-body "breaches" {:domain domain})))

(defn get-for-name
  "Gets a breach by its name"
  {:see "https://haveibeenpwned.com/api/v2/#SingleBreach"}
  [name]
    {:pre [(not (nil? name))]} 
    (api/get-body (str "breach/" name)))

(defn get-data-classes
  "Gets all breach data classes"
  {:see "https://haveibeenpwned.com/api/v2/#AllDataClasses"}
  [] 
    (api/get-body "dataclasses"))