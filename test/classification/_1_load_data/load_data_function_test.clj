(ns classification.-1-load-data.load-data-function-test
  (:require [midje.sweet :refer [fact facts throws =>]])
  (:require [clojure.test :refer :all])
  (:require [classification.-1-load-data.load-data-function :refer [read-file get-dataset parse-value convert-dataset]])
  (:import (java.io FileNotFoundException)))

(deftest read-file-test
  (facts "read-file testing"
         (fact "returns list" (seq? (read-file "src/classification/_1_load_data/Carseats.csv")) => true)
         (fact "fail if wrong input" (seq? (read-file "")) => (throws FileNotFoundException))
         (fact "NullPointerException if no input is set" (seq? (read-file nil)) => (throws IllegalArgumentException))))

(deftest parse-value-test
  (facts "parse-value testing"
         (fact (number? (parse-value "1")) => true)
         (fact (number? (parse-value "1.2")) => true)
         (fact (boolean? (parse-value "Yes")) => true)
         (fact (boolean? (parse-value "No")) => true)
         (fact (string? (parse-value "something")) => true)
         (fact (parse-value nil) => (throws NullPointerException))))

(deftest get-dataset-test
  (facts "get-dataset testing"
         (fact (seq? (get-dataset "src/classification/_1_load_data/Carseats.csv")) => true)
         (fact (seq? (get-dataset "")) => (throws FileNotFoundException))
         (fact (seq? (get-dataset nil)) => (throws IllegalArgumentException))))

(deftest convert-dataset-test
  (facts "convert-dataset testing"
         (fact (seq? (convert-dataset "src/classification/_1_load_data/Carseats.csv")) => true)
         (fact (seq? (convert-dataset "")) => (throws FileNotFoundException))
         (fact (seq? (convert-dataset nil)) => (throws IllegalArgumentException))))
