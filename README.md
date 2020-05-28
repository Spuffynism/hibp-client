# hibp-client

hibp-client is a clojure library that wraps the [Have I been pwned API](https://haveibeenpwned.com/API/v2).

## Usage

See [examples](test/hibp_client/examples.clj).

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
 - add ability to set user agent
 - write tests
