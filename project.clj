(defproject hibp-client "2.0.0"
  :description "Have I been pwned clojure client"
  :url "https://github.com/Spuffynism/hibp-client"
  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]
                 [camel-snake-kebab "0.4.0"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.24.1"]]
                   :test-refresh {:quiet true
                                  :changes-only true}}}
  :test-selectors {:default #(not (:integration %))
                   :integration :integration
                   :all (constantly true)})
