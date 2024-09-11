(ns classification.-3-create-groups.create-groups-function-test
  (:require [clojure.test :refer :all])
  (:require [midje.sweet :refer [=> facts fact throws]])
  (:require [classification.-3-create-groups.create-groups-function :refer [sort-ascending count-upper-percentile get-upper-percentile convert-to-groups estimate-high-sale]])
  (:require [classification.-1-load-data.load-data-function :refer [get-dataset]])
  (:import (java.io FileNotFoundException)))

(deftest sort-ascending-test
  (facts "sort-ascending testing"
         (fact (seq? (sort-ascending (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)
         (fact (seq? (sort-ascending (get-dataset "src"))) => (throws FileNotFoundException))
         (fact (seq? (sort-ascending (get-dataset nil))) => (throws IllegalArgumentException))))

(deftest count-upper-percentile-test
  (facts "get-upper-percentile testing"
         (fact (number? (count-upper-percentile (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))

(deftest get-upper-percentile-test
  (facts "get-upper-percentile testing"
         (fact (seq? (get-upper-percentile (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))

(deftest convert-to-groups-test
  (facts "convert-to-group testing"
         (fact (seq? (convert-to-groups (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))



(deftest estimate-high-sale-test
  (facts "estimate-high-sale testing"
         (fact (estimate-high-sale 1.5 1) => true)
         (fact (estimate-high-sale 0.5 1) => false)))
