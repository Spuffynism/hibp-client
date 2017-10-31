(ns hibp-client.pwned-password
  (:require [hibp-client.api :as api]))

(defn pwned?
  "Indicates if the specified password is pwned"
  {:see "https://haveibeenpwned.com/api/v2/#PwnedPasswords"}
  ([password]
    {:pre [(not (nil? password))]}
    (api/exists? "pwnedpassword/" {:Password password}))
  ([password original-password-is-a-hash] 
    {:pre [(not (nil? password))]}
    (api/exists? "pwnedpassword/" {:Password password}
      {:originalPasswordIsAHash original-password-is-a-hash})))