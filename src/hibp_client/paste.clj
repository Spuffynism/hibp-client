(ns hibp-client.paste
  (:require [hibp-client.api :as api]))

(defn get-for-account
  "Gets all pastes for a specific account"
  {:see "https://haveibeenpwned.com/api/v2/#PastesForAccount"}
  [account]
    {:pre [(not (nil? account))]} 
    (api/get-body (str "pasteaccount/" account)))