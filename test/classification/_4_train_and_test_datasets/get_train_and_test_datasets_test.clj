(ns classification.-4-train-and-test-datasets.get-train-and-test-datasets-test
  (:require [clojure.test :refer :all])
  (:require [midje.sweet :refer [=> fact facts]])
  (:require [classification.-4-train-and-test-datasets.get-train-and-test-datasets :refer [deterministic-shuffle get-test-and-train-datasets]])
  (:require [classification.-1-load-data.load-data-function :refer [get-dataset]]))

(deftest deterministic-shuffle-test
  (facts "deterministic-shuffle testing"
         (fact (vector? (deterministic-shuffle (get-dataset "src/classification/_1_load_data/Carseats.csv") 0.27 )) => true)))

(deftest get-test-and-train-datasets-test
  (facts "get-test-and-train-datasets testing"
         (let [dataset (get-test-and-train-datasets (get-dataset "src/classification/_1_load_data/Carseats.csv"))]
           (fact "if result is hash map" (map? dataset) => true)
           (fact "if contains :train" (contains? dataset :train) => true)
           (fact "if contains :test" (contains? dataset :test) => true))))
