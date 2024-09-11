(ns classification.-3-create-groups.create-groups-function
  (:require [classification.-1-load-data.load-data-function :as load-data]
            [clojure.math :as math]))


(defn sort-ascending
  "Sorting dataset ascending according to Sales column"
  [dataset]
  (sort-by first dataset))

;(load-data/get-dataset dataset)

(defn count-upper-percentile
  "Count upper percentile
  Arguments:
      - dataset: relative location to 'src' folder
  Returns:
      - value of upper percentile"
  [dataset]
  (math/round (* 0.75 (count dataset))))

(defn get-upper-percentile
  "Get upper percentile (75%) of dataset"
  [dataset]
  (nth (sort-ascending dataset) (count-upper-percentile dataset)))

(defn estimate-high-sale
  "Estimates if sale is high sale
  Arguments:
      - sale-value Value of sale
  Returns:
      - True or False"
  [sale-value third-percentile]
  (if (> sale-value third-percentile)
    true
    false))

(defn convert-row
  "Delete Sales column and add HighSales column"
  [row third-percentile]
  (vec (drop 1 (conj (vec row) (estimate-high-sale (first row) third-percentile)))))

(defn convert-to-groups
  "Converts dataset in 2 groups:
  group with high sales true
  and
  group with high sales false"
  [dataset]
  (let [third-percentile (first (get-upper-percentile dataset))]
    (map (fn [row] (convert-row row third-percentile)) dataset)))

(convert-to-groups (load-data/get-dataset "src/classification/_1_load_data/Carseats.csv"))