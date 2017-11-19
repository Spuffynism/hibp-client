(ns hibp-client.examples
  (:gen-class)
  (:require [hibp-client.pwned-password :as pwned-password]
    [hibp-client.breach :as breach]
    [hibp-client.paste :as paste]))

(defn -main [& args]

  ;; Checking if a password is pwned
  (println (pwned-password/pwned? "12345"))
  ;; => true

  ;; Getting breaches for a specific account
  (println (breach/get-for-account "test@example.com"))
  ;; => ({:description In approximately March 2015, the free web hosting prov...

  ;; Getting breaches for a specific account, filtering the results
  (println (breach/get-for-account "test@example.com" 
    {:truncate-response true}))
  ;; => ({:name 000webhost} {:name Adobe} {:name BitcoinTalk} {:name Bitly} {...

  ;; Getting all breaches
  (println (breach/get-all))
  ;; => ({:description In approximately March 2015, the free web hosting prov...

  ;; Getting all breaches for a domain
  (println (breach/get-all "adobe.com"))
  ;; => ({:description In October 2013, 153 million Adobe accounts were breac...

  ;; Getting a breach by its name
  (println (breach/get-for-name "Adobe"))
  ;; => {:description In October 2013, 153 million Adobe accounts were breach...

  ;; Getting all breach data classes
  (println (breach/get-data-classes))
  ;; => (Account balances Age groups Ages Astrological signs Auth tokens Avat...

  ;; Getting all pastes for a specific account
  (println (paste/get-for-account "test@example.com")))
  ;; => ({:source Pastebin, :id suPshHZ1, :title nil, :date 2017-09-06T03:41:...
