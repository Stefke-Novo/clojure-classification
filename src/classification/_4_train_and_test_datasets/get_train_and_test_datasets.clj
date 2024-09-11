(ns classification.-4-train-and-test-datasets.get-train-and-test-datasets
  (:require [classification.-3-create-groups.create-groups-function :as convert-dataset]
            [clojure.math :as math])
  (:import (clojure.lang RT)
           (java.util ArrayList Collection Collections Random)))

(defn deterministic-shuffle
  [^Collection coll seed]
  (let [al (ArrayList. coll)
        rng (Random. seed)]
    (Collections/shuffle al rng)
    (RT/vector (.toArray al))))


(defn get-test-and-train-datasets
  "Gets test and train datasets in hashmap"
  [dataset]
  (let [data-set (convert-dataset/convert-to-groups dataset) result (split-at (math/round (* 0.8 (count data-set))) (deterministic-shuffle data-set 27))]
    {
     :train (nth result 0)
     :test (nth result 1)}))
