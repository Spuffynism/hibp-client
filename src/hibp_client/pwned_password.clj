(ns hibp-client.pwned-password
  (:require [hibp-client.api :as api]))

(defn list-for-range
  "Searches for password hash suffixes by a partial sha1 hash 5-character prefix"
  {:see "https://haveibeenpwned.com/API/v3#SearchingPwnedPasswordsByRange"}
  ([hash]
   (list-for-range hash false))
  ([hash add-padding]
   {:pre [(= (count hash) 5)]}
   (api/get-body
     nil
     (merge {:complete-path (str "https://api.pwnedpasswords.com/range/" hash)}
            (if add-padding {:headers {"add-padding" "true"}})))))