[![Clojars Project](https://img.shields.io/clojars/v/hibp-client.svg)](https://clojars.org/hibp-client)

# hibp-client

hibp-client is a clojure library that wraps the [Have I been pwned API v3](https://haveibeenpwned.com/API/v3).

## Usage

See [examples](test/hibp_client/examples.clj).

Some calls need an api key. See [Authorisation](https://haveibeenpwned.com/API/v3#Authorisation) from the official HIBP documentation.

## Running tests

Run unit tests with `lein test`

Run integration tests with `lein test :integration`

Run all tests with `lein test :all`

## Development

Use `lein test-refresh` to continuously run tests as code is changed.
