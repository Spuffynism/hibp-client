(ns hibp-client.pwned-password
  (:require [hibp-client.api :as api]))

(defn pwned?
  ([password]
    {:pre [(not (nil? password))]}
    (api/exists? "pwnedpassword/" {:Password password}))
  ([password original-password-is-a-hash] 
    {:pre [(not (nil? password))]}
    (api/exists? "pwnedpassword/" {:Password password}
      {:originalPasswordIsAHash original-password-is-a-hash})))