(ns classification.-6-confusion-matrix.confusion-matrix
  (:require [classification.-5-classification-algorithm.classification-algorithm :as algorithm]
            [classification.-4-train-and-test-datasets.get-train-and-test-datasets :as datasets]
            [classification.-1-load-data.load-data-function :as load-data])
  (:import (clojure.core VecSeq)))


(defn ask-question
  [value, question]
  (if (instance? Number value)
    (if (>= value question)
      true
      false)
    (if (identical? value question)
      true
      false)))

(defn test-row
  [row classification-tree]
  (if (ask-question (nth row (:column classification-tree)) (:question classification-tree))
    (if (identical? (type (:true classification-tree)) VecSeq)
      (conj row false)
      (test-row row (:true classification-tree)))
    (if (identical? (type (:false classification-tree)) VecSeq)
      (conj row true)
      (test-row row (:false classification-tree)))))

(defn train-classification
  [dataset]
  (let [prepared-dataset (datasets/get-test-and-train-datasets dataset)
        train-dataset (:train prepared-dataset)
        test-dataset (:test prepared-dataset)
        classification-tree (algorithm/create-tree train-dataset)]
    (map (fn [row]
           (test-row row classification-tree)))
    test-dataset))
(train-classification (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv"))

(defn build-confusion-matrix
  [result-dataset]
  (reduce (fn [res x] (let [second-last (- (count x) 2)]
                        (if (identical? false (last x))
                          (if (identical? (last x) (nth x second-last))
                            (assoc res :TP (+ (:TP res) 1))
                            (assoc res :FP (+ (:FP res) 1)))
                          (if (identical? (last x) (nth x second-last))
                            (assoc res :TN (+ (:TN res) 1))
                            (assoc res :FN (+ (:FN res) 1)))))) {:TP 0 :TN 0 :FP 0 :FN 0} result-dataset))

(build-confusion-matrix (train-classification (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv")))
(defn calculate-measures
  [confusion-matrix]
  {
   :accuracy (double (/ (+ (:TP confusion-matrix) (:TN confusion-matrix)) (+ (:TP confusion-matrix) (:TN confusion-matrix) (:FP confusion-matrix (:FN confusion-matrix)))))
   :recall (double (/ (:TP confusion-matrix) (+ (:TP confusion-matrix) (:FN confusion-matrix))))
   :precision (double (/ (:TP confusion-matrix) (+ (:TP confusion-matrix) (:FP confusion-matrix))))
   })
(calculate-measures (build-confusion-matrix (train-classification (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv"))))


