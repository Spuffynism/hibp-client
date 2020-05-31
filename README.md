# hibp-client

hibp-client is a clojure library that wraps the [Have I been pwned API v3](https://haveibeenpwned.com/API/v3).

## Usage

See [examples](test/hibp_client/examples.clj).

Some calls need an api key. See [Authorisation](https://haveibeenpwned.com/API/v3#Authorisation) from the official HIBP documentation.

## Installation

hibp-client is available as a Maven artifact from [Clojars](https://clojars.org/hibp-client).

## Running tests
Start a lein repl with `lein repl` and then run:

```
(require '[clojure.test :refer [run-tests]])
(require 'hibp-client.all-test :reload-all)
(run-tests 'hibp-client.all-test)
```

## TODO

 - write tests
