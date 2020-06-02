(ns hibp-client.test-fixtures
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.api :as api]))

(defn- passthrough-get-json-body
  "Returns the given arguments as a hash"
  ([path] {:path path})
  ([path configuration] {:path path :configuration configuration}))

(defn- passthrough-get-body
  "Returns the given arguments as a hash"
  ([path configuration] {:path path :configuration configuration})
  ([path configuration extra-configuration] {:path path
                                             :configuration configuration
                                             :extra-configuration extra-configuration}))

(defn make-api-passthrough
  "Makes the api passthrough"
  [f]
  (with-redefs [api/get-json-body passthrough-get-json-body
                api/get-body passthrough-get-body]
    (f)))