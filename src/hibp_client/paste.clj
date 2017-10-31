(ns hibp-client.paste
  (:require [hibp-client.api :as api]))

(defn get-for-account 
  [account]
  {:pre [(not (nil? account))]} 
  (api/get-body (str "pasteaccount/" account)))