(ns classification.-5-classification-algorithm.classification-algorithm-test
  (:require [clojure.test :refer :all])
  (:require [midje.sweet :refer [ => facts fact]])
  (:require [classification.-5-classification-algorithm.classification-algorithm :refer [get-column get-row-size generate-tree best-question analyze-by-questions create-tree]])
  (:require [classification.-1-load-data.load-data-function :refer [get-dataset]]))

(deftest get-column-test
  (facts "get-column testing"
         (fact (seq? (get-column 0 (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))

(deftest get-row-size-test
  (facts "get-row-size testing"
         (fact (instance? Number (get-row-size (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))

(deftest create-tree-test
  (facts "generate-tree testing"
    (fact (map? (create-tree (get-dataset "src/classification/_1_load_data/Carseats.csv"))) => true)))
