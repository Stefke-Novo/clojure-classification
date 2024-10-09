# classification

Classification is process that's based on several steps of execution:
1. load data
2. describe data
3. define result data for machine learning
4. build function for building test and train set under constant random condition
5. build train machine learning algorithm
6. use machine learning alg. for training
7. build confusion matrix
8. get results


Classification project is consisted of several steps:
+ Load data
+ Describe data - presenting whole dataset on console
+ Create groups - creating test and train groups of data for training model 
+ Get train and test dataset - to distinguish test and train datasets by building deterministic shuffle 
+ Classification algorithm - creating classification algorithm based on train dataset and later testing it with test dataset
+ Confusion matrix - building confusion matrix for calculating statistic metrics like precision, efficiency, callback and f1 value

For that reason, project is separated into 6 documents with roles listed above:
1. load_data_function.clj
2. describe_data_function.clj
3. create_groups_function.clj
4. get_train_and_test_datasets.clj
5. classification_algorithm.clj
6. confusion_matrix.clj

Below will be presented functions that each of the files contains.

## Usage

### load_data_function.clj
+ parse-file 
   
   Parameters:
   + path - path to CSV file from src folder
   
    Result: List of all observations
```clojure
(defn read-file [path])
```

+ parse-value

    Parameters:
    * number_value - value in dataset
   
    Result: Converted value into its type
```clojure
(defn parse-value [number_value])
```

+ parse-row

    Parameters:
    + row - dataset row
  
    Result: Converted dataset row
```clojure
(defn parse-row [row])
```

+ convert-dataset

    Parameters:
    + dataset-URL - Location where csv file is (relative path to src folder)
  
    Result: Converted dataset
```clojure
(defn convert-dataset [dataset-URL])
```

+ get-dataset

    Parameters:
    + dataset - Location where csv file is
  
    Result: Dataset that doesn't contain heading (column names)
```clojure
(defn get-dataset [dataset])
```

### describe_data_function.clj

+ get-dataset-type
  
    Parameters:
    + dataset-url: CSV file location
  
    Result: List of types of dataset columns
```clojure
(defn get-dataset-type [dataset-url])
```

+ get-dataset-heading

    Parameters:
    + dataset-path: Path to CSV file

    Result: List of column names
```clojure
(defn get-dataset-heading [dataset-path])
```

+ print-types
  
    Parameters:
    + dataset-path: Path to CSV file
  
    Result: void
```clojure
(defn print-types [dataset-path])
```

### create_groups_function.clj

+ sort-ascending
  
  Parameters:
  + dataset - Location to CSV file

  Result: Sorted dataset ascending by column Sales
```clojure
(defn sort-ascending [dataset])
```

+ count-upper-percentile

  Parameters:
  + dataset: Location to CSV file
  
  Result: Number that represents element on 75% dataset distribution position
```clojure
(defn count-upper-percentile [dataset])
```

+ get-upper-percentile

  Parameters: 
  + dataset - Location to CSV file

  Result: Number that represents 75% percentile
```clojure
(defn get-upper-percentile [dataset])
```

+ estimate-high-sale

  Parameters:
  + sale-value - value of Sales column
  + third-percentile - number of 3rd percentile

  Result: Added column with value true or false
```clojure
(defn estimate-high-sale [sale-value third-percentile])
```

+ convert-row

  Parameters:
  + row - Dataset row 
  + third-percentile - value of 3rd percentile

  Result: Removed 1st column on which is based classification and added if the entity belongs to 3rd percentile or not
```clojure
(defn convert-row [row third-percentile])
```

+ convert-to-groups

  Parameters:
  + dataset: Location to CSV file

  Result: Converted dataset into 2 groups (1st that is less or equals to 3rd percentile and 2nd that's greater than 3rd percentile) 
```clojure
(defn convert-to-groups [dataset])
```
### get_train_and_test_datasets

+ deterministic-shuffle

  Parameters:
  + coll - dataset 
  + seed - Random number that's going to orient random function to behave same way
  
    Result: Random number

```clojure
(defn deterministic-shuffle [^Collection coll seed])
```
+ get-test-and-train-datasets

  Parameters:
  + dataset - Loaded dataset

  Result: train and test datasets
```clojure
(defn get-test-and-train-datasets [dataset])
```

### classification_algorithm

+ get-column

  Parameters:
  + dataset - Loaded dataset from CSV file

  Result: Column from dataset
```clojure
(defn get-column [column dataset])
```

+ get-row-size

  Parameters: 
  + dataset: Loaded dataset from CSV file

  Result: Row size number
```clojure
(defn get-row-size [dataset])
```

+ ask-question

  Parameters:
  + question: term that will divide dataset into 2 groups
  + item: value that's going to be analyzed and it's going to determine which group the observation it will be part of

  Result: Boolean value if the observation belongs to wanted group
```clojure
(defn ask-question [question item])
```
+ group-rows
  
  Parameters:
  + dataset: Dataset loaded from CSV file
  + place: Place of column that's been examined
  + question: Value that's dividing dataset into 2 groups

  Result: Wanted (true) and unwanted (false) sets of data
```clojure
(defn group-rows [dataset place question])
```
+ count-positive-class
  
  Parameters:
  + item: Observation that's been analyzed
  
  Result: 1 if the observation belongs to wanted class and 0 if observation doesn't belong to wanted class
```clojure
(defn count-positive-class [item])
```
+ calculate-gini-impurity

  Parameters:
  + item: Observation
  
  Result: Calculated gini impurity
```clojure
(defn calculate-gini-impurity [item])
```
+ analyze-by-question

  Parameters:
  + dataset: Loaded dataset from CSV file

  Result: all results of analysis based on all questions from dataset
```clojure
(defn analyze-by-questions [dataset])
```
+ best-question
  
  Parameters:
  + questions: Possible data that divides groups with estimated impact
  
  Result: The value which makes the biggest impact on data separation
```clojure
(defn best-question [questions])
```
+ create-tree

  Parameters:
  + dataset: Dataset loaded from CSV file

  Result: Decision tree based on Gini impurity algorithm
```clojure
(defn create-tree [dataset])
```
### confusion_matrix

+ ask-question (analyzed already)
+ test-row
  
  Parameters:
  + row: Observation that's going to be estimated by classification tree
  + classification-tree - classification model that will be used ofr estimation
  
  Result: Classified results into 2 groups based on classification tree
```clojure
(defn test-row [row classification-tree])
```
+ train-classification

  Parameters:
  + dataset: Dataset loaded from CSV file
  
  Result: Divided test and train dataset that's been used for forming classification model
```clojure
(defn train-classification [dataset])
```

+ build-confusion-matrix
  
  Parameters:
  + result-dataset: Dataset classified into 2 groups based on classification model

  Result: Object that contains 4 groups (false-positive, false-negative, true-positive and true-negative group)
```clojure
(defn build-confusion-matrix [result-dataset])
```
+ calculate-measures

  Parameters:
  + confusion-matrix: Previously mentioned 4 groups

  Result: precision, recall, accuracy and f1 measures that describe degree of successful separating groups
```clojure
(defn calculate-measures [confusion-matrix])
```

## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
