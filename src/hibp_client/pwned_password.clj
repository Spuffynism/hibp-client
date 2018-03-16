(ns hibp-client.pwned-password
  (:require [hibp-client.api :as api]))

(defn pwned?
  "Indicates if the specified password is pwned"
  {:see "https://haveibeenpwned.com/api/v2/#PwnedPasswords"}
  ([password]
    (pwned? password nil))
  ([password original-password-is-a-hash] 
    {:pre [(not (nil? password))]}
    (api/exists? "pwnedpassword/" {:Password password}
      (if (not (nil? original-password-is-a-hash))
        {:originalPasswordIsAHash original-password-is-a-hash} {}))))