(ns classification.-6-confusion-matrix.confusion-matrix-test
  (:require [clojure.test :refer :all])
  (:require [midje.sweet :refer [ => fact facts]])
  (:require [classification.-6-confusion-matrix.confusion-matrix :refer [ask-question train-classification build-confusion-matrix calculate-measures]])
  (:require [classification.-1-load-data.load-data-function :refer [get-dataset]]))

(deftest ask-question-test
  (facts "ask-question testing"
         (fact (ask-question 3 2) => true)
         (fact (ask-question 1 2) => false)
         (fact (ask-question true true) => true)
         (fact (ask-question false true) => false)
         (fact (ask-question "item" "item") => true)
         (fact (ask-question "item" "item1") => false)))


(deftest train-classification-test
  (facts "train-classification testing"
         (fact (seq? (train-classification (get-dataset "src/classification/_1_load_data/Carseats.csv")))) => true))

(deftest build-confusion-matrix-test
  (facts "build-confusion-matrix testing"
         (fact "is result type map?" (map? (build-confusion-matrix (train-classification (get-dataset "src/classification/_1_load_data/Carseats.csv")))) => true)))

(deftest calculate-measures-test
  (facts "calculate-measures testing"
         (fact "is result type map?" (map? (calculate-measures (build-confusion-matrix (train-classification (get-dataset "src/classification/_1_load_data/Carseats.csv"))))) => true)))
