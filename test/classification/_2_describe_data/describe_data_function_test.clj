(ns classification.-2-describe-data.describe-data-function-test
  (:require [clojure.test :refer :all])
  (:require [midje.sweet :refer [=> facts fact throws]])
  (:require [classification.-2-describe-data.describe-data-function :refer [get-dataset-type get-dataset-heading]])
  (:import (java.io FileNotFoundException)))

(deftest get-dataset-type-test
  (facts "get-dataset-type testing"
         (fact (seq? (get-dataset-type "src/classification/_1_load_data/Carseats.csv")) => true)
         (fact (get-dataset-type "") => (throws FileNotFoundException))
         (fact (get-dataset-type nil) => (throws IllegalArgumentException))))

(deftest get-dataset-heading-test
  (facts "get-dataset-heading testing"
         (fact (vector? (get-dataset-heading "src/classification/_1_load_data/Carseats.csv")) => true)
         (fact (seq? (get-dataset-heading "")) => (throws FileNotFoundException))
         (fact (seq? (get-dataset-heading nil)) => (throws IllegalArgumentException))))
