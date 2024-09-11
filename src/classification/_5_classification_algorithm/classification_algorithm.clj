(ns classification.-5-classification-algorithm.classification-algorithm
  (:require [classification.-1-load-data.load-data-function :as load-data]
            [clojure.math :as math]))


(defn get-column
  "Gets column from dataset"
  [column dataset]
  (if (== 0 (count dataset))
    []
    (map (fn [item]
           (nth item column))
         dataset)))
(defn get-row-size
  "Returns row size"
  [dataset]
  (if (== 0 (count dataset))
    0
    (count (nth dataset 0))))

(defn ask-question
  "Asking question"
  [question item]
  (if (instance? Number item)
    (>= item question)
    (identical? item question))
    )

(defn group-rows
  [dataset place question]
  (reduce
    (fn
      [groups row]
      (let [value (nth row place)]
        (if (ask-question question value)
          (assoc groups :true (conj (:true groups) row))
          (assoc groups :false (conj (:false groups) row))))
      )
    {:true [] :false []}
    dataset
    ))

(defn count-positive-class
  [item]
  (reduce (fn [res x]
            (+ res (if (identical? (last x) false)
                               1
                               0)))
          0
          item))
(defn calculate-gini-impurity
  [item]
  (let [count-true-group (count-positive-class (:true item)) count-false-group (count-positive-class (:false item)) count-all-true (count (:true item)) count-all-false (count (:false item))]
    (- 1 (if (== count-all-true 0)
           0
           (math/pow (/ count-true-group (+ count-all-true count-all-false)) 2))
       (if (== count-all-false 0)
           0
           (math/pow (/ count-false-group (+ count-all-true count-all-false)) 2))))
  )

(defn analyze-by-questions
  "Get all questions"
  [dataset]
  (reduce
    (fn
      [res place]
      (concat res
              (reduce (fn [question-list question]
                        (let [groups (group-rows dataset place question)]
                          (conj question-list {:question question :gini (calculate-gini-impurity groups) :column place :true (:true groups) :false (:false groups)}))
                        )
                      []
                      (distinct (get-column place dataset))
                      )))
    []
    (range (- (get-row-size dataset) 1))))