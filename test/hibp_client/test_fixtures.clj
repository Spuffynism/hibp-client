(ns hibp-client.test-fixtures
  (:require [clojure.test :refer :all]
            [hibp-client.breach :refer :all]
            [hibp-client.api :as api]))

(defn- passthrough-get-json-body
  "Returns the given arguments as a hash"
  ([path] {:path path})
  ([path configuration] {:path path :configuration configuration}))

(defn redefine-get-json-body
  "Redefines get json body calls"
  [f]
  (with-redefs [api/get-json-body passthrough-get-json-body]
    (f)))