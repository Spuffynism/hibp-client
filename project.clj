(defproject hibp-client "0.1.0-SNAPSHOT"
  :description "Have I been pwned clojure client"
  :url "https://gitlab.com/ndlr/hibp-clj-client"
  :license {:name "MIT License"
            :url "https://choosealicense.com/licenses/mit/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
    [clj-http "3.7.0"]
    [cheshire "5.8.0"]
    [camel-snake-kebab "0.4.0"]]
  :main hibp-client.core)
