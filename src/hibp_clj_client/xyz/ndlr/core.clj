(ns hibp-clj-client.xyz.ndlr.core
  (:require [hibp-clj-client.xyz.ndlr.breach :as breach]))

(defn -main [& args]
  (println (breach/get-for-account "test@example.com" {
    :truncate-response true :include-unverified true})))
