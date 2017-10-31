(ns hibp-client.core
  (:require [hibp-client.breach :as breach]))

(defn -main [& args]
  (println (breach/get-data-classes)))
