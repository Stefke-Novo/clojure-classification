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

(defn best-question
  "Returns best question that makes the biggest impact on distinguishing positive from negative groups"
  [questions]
  (reduce (fn [res x]
            (if (and (> (count (:true x)) 0) (> (count (:false x)) 0) (> 1 (:gini x)))
              x
              res)) questions))
(nth (analyze-by-questions (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv")) 0)
(map (fn [item] (:gini item)) (analyze-by-questions (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv")))
(best-question (analyze-by-questions (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv")))
(defn generate-tree
  "Create classification tree from dataset"
  [dataset]
  (print (:gini dataset) "\n")
  (if (or (<= (:gini dataset) 0.2) (== 1 (:gini dataset)))
    dataset
    {:question (:question dataset)
     :gini (:gini dataset)
     :column (:column dataset)
     :true (let [right-data (best-question (analyze-by-questions (:true dataset)))] (if (empty? right-data) right-data (generate-tree right-data)))
     :false (let [false-data (best-question (analyze-by-questions (:false dataset)))] (if (empty? false-data) false-data (generate-tree false-data)))}))

(defn create-tree
  "Creates tree from dataset"
  [dataset]
  (generate-tree (best-question (analyze-by-questions dataset))))