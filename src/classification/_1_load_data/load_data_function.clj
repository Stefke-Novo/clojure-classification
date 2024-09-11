(ns classification.-1-load-data.load-data-function
  (:require [clojure.data.csv :as csv]
           [clojure.java.io :as io]))

(defn read-file
  "Reading file
  Arguments:
      - path: Path from src folder to csv file
  Returns:
      - clojure.lang.LazySeq"
  [path]
  (with-open [reader (io/reader path)]
    (doall
      (csv/read-csv reader))))

(defn parse-value
  "Converts string into integer if the value has only digits in it
  Arguments:
      - value: value of string from data set
  Returns:
      java.lang.Object"
  [number_value]
  (if (re-matches #"\d+" number_value)
    (Integer/parseInt number_value)
    (if (re-matches #"\d+.\d+" number_value)
      (Double/parseDouble number_value)
      (if (= "Yes" number_value)
        true
        (if (= "No" number_value)
          false
          number_value)))))

(defn parse-row
  "Parsing each element of row to belonging type
  Arguments:
      - row: java.lang.LazySeq
  Returns:
      - java.lang.LazySeq"
  [row]
  (map parse-value row))

(defn convert-dataset
  "Converts value of each observation in dataset to true data type and returns converted dataset.
  Arguments:
      - dataset-url: URL to dataset from src folder
  Returns:
      - java.lang.LazySeq"
  [dataset-url]
  (map (fn [x] (parse-row x)) (read-file dataset-url)))

(convert-dataset "src/classification/_1_load_data/Carseats.csv")

(defn get-dataset
  "Get data without headings"
  [dataset]
  (drop 1 (convert-dataset dataset)))