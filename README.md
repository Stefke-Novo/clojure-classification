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


    (defn read-file [path])

+ parse-value

    Parameters:
    * number_value - value in dataset
   
    Result: Converted value into its type


    (defn parse-value [number_value])

+ parse-row

    Parameters:
    + row - dataset row
  
    Result: Converted dataset row


    (defn parse-row [row])

+ convert-dataset

    Parameters:
    + dataset-URL - Location where csv file is (relative path to src folder)
  
    Result: Converted dataset


    (defn convert-dataset [dataset-URL])

+ get-dataset

    Parameters:
    + dataset - Location where csv file is
  
    Result: Dataset that doesn't contain heading (column names)


    (get-dataset [dataset])

### describe_data_function.clj

+ get-dataset-type
  
    Parameters:
    + dataset-url: CSV file location
  
    Result: List of types of dataset columns


    (get-dataset-type [dataset-url])

+ get-dataset-heading

    Parameters:
    + dataset-path: Path to CSV file

    Result: List of column names


    (get-dataset-heading [dataset-path])

+ print-types
  
    Parameters:
    + dataset-path: Path to CSV file
  
    Result: void

    
    (print-types [dataset-path])

### create_groups_function.clj

+ sort-ascending
  
  Parameters:
  + dataset - Location to CSV file

  Result: Sorted dataset ascending by column Sales

    
    (sort-ascending [dataset])

+ count-upper-percentile

  Parameters:
  + dataset: Location to CSV file
  
  Result: Number that represents element on 75% dataset distribution position


    (count-upper-percentile [dataset])

+ get-upper-percentile

  Parameters: 
  + dataset - Location to CSV file

  Result: Number that represents 75% percentile


    (get-upper-percentile [dataset])

+ estimate-high-sale

  Parameters:
  + sale-value - value of Sales column
  + third-percentile - number of 3rd percentile

  Result: Added column with value true or false


    (estimate-high-sale [sale-value third-percentile])

+ convert-row

  Parameters:
  + row - Dataset row 
  + third-percentile - value of 3rd percentile

  Result: Removed 1st column on which is based classification and added if the entity belongs to 3rd percentile or not


    (convert-row [row third-percentile])

+ convert-to-groups

  Parameters:
  + dataset: Location to CSV file

  Result: Converted dataset into 2 groups (1st that is less or equals to 3rd percentile and 2nd that's greater than 3rd percentile) 


    (convert-to-groups [dataset])

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
