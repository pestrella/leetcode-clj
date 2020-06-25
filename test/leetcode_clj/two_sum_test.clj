(ns leetcode-clj.two-sum-test
  (:require [leetcode-clj.two-sum :refer [two-sum]]
            [clojure.test :refer :all]))

(deftest two-sum-simple
  (testing "[1 4 5 3 2], target = 4"
    (is (= '(0 3) (two-sum [1 4 5 3 2] 4))))

  (testing "[2 2 4 3], target = 4"
    (is (= '(0 1) (two-sum [2 2 4 3] 4)))))

(deftest two-sum-no-solution
  (testing "[1 7 3], target = 5"
    (is (nil? (two-sum [1 7 3] 5)))))
