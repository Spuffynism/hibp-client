(ns hibp-client.paste
  (:require [hibp-client.api :as api]))

(defn get-for-account
  "Gets all pastes for a specific account"
  {:see "https://haveibeenpwned.com/API/v3#PastesForAccount"}
  [api-key account]
  {:pre [(not (nil? account))]}
  (api/get-json-body (str "/pasteaccount/" account) {:api-key api-key}))