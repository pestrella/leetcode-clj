(ns leetcode-clj.diagonals-test
  (:require [leetcode-clj.diagonals :refer [diagonals]]
            [clojure.test :refer :all]))

(deftest diagonals-test
  (testing "diagonals: 3 * 4"
    (let [diags (diagonals [[1  2  3  4]
                            [5  6  7  8]
                            [9  10 11 12]])]
      (is (= [[1]
              [2 5]
              [3 6 9]
              [4 7 10]
              [8 11]
              [12]]
             diags))))

  (testing "diagonals: 2 * 6"
    (let [diags (diagonals [[1 2 3 4  5  6]
                            [7 8 9 10 11 12]])]
      (is (= [[1]
              [2 7]
              [3 8]
              [4 9]
              [5 10]
              [6 11]
              [12]]
             diags))))

  (testing "diagonals: 6 * 2"
    (let [diags (diagonals [[1 2]
                            [3 4]
                            [5 6]
                            [7 8]
                            [9 10]
                            [11 12]])]
      (is (= [[1]
              [2  3]
              [4  5]
              [6  7]
              [8  9]
              [10 11]
              [12]]
             diags)))))
