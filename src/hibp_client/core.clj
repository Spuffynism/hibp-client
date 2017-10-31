(ns hibp-client.core
  (:require [hibp-client.pwned-password :as pwned-password]))

(defn -main [& args]
  (println (pwned-password/pwned? "12345")))
