(ns hibp-client.breach
  (:require [hibp-client.api :as api]
            [clojure.set :as set]))

(defn get-for-account
  "Gets breaches for a specific account. :domain, :truncate-response and 
  :include-unverified can be specified to filter the response"
  {:see "https://haveibeenpwned.com/API/v3#BreachesForAccount"}
  ([api-key account]
   (get-for-account api-key account {}))
  ([api-key account filter]
   (let [params {:query-params (set/rename-keys filter {:truncate-response :truncateResponse
                                                        :include-unverified :includeUnverified})
                 :api-key api-key}]
     (api/get-json-body (str "/breachedaccount/" account) params))))

(defn get-all
  "Gets all breaches. A domain name can be specified to filter the response"
  {:see "https://haveibeenpwned.com/API/v3#AllBreaches"}
  ([]
   (get-all nil))
  ([domain]
   (api/get-json-body
     "/breaches"
     (if domain {:query-params {:domain domain}}))))

(defn get-for-name
  "Gets a breach by its name"
  {:see "https://haveibeenpwned.com/API/v3#SingleBreach"}
  [name]
  {:pre [(not (nil? name))]}
  (api/get-json-body (str "/breach/" name)))

(defn get-data-classes
  "Gets all breach data classes"
  {:see "https://haveibeenpwned.com/API/v3#AllDataClasses"}
  []
  (api/get-json-body "/dataclasses"))