(ns classification.-2-describe-data.describe-data-function
  (:require [classification.-1-load-data.load-data-function :as load-data]))

(defn get-dataset-type
  "Gets types of values for each observation in dataset.
  Arguments:
      - dataset-url: URL to file from src folder"
  [dataset-url]
  (map type (nth (load-data/convert-dataset dataset-url) 1)))

(get-dataset-type "src/classification/_1_load_data/Carseats.csv")

(defn get-dataset-heading
  "Gets heading of dataset that describes data of each observation.
  Arguments:
      - dataset-path: Path to dataset from src folder."
  [dataset-path]
  (nth (load-data/read-file dataset-path) 0))

(get-dataset-heading "src/classification/_1_load_data/Carseats.csv")

(defn print-types
  "Prints type information for observation attributes."
  [dataset-path]
  (map (fn [x y] {x y}) (get-dataset-heading "src/classification/_1_load_data/Carseats.csv") (get-dataset-type dataset-path)))

(print-types "src/classification/_1_load_data/Carseats.csv")


