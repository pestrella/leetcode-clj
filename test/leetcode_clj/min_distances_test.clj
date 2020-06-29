(ns leetcode-clj.min-distances-test
  (:require [leetcode-clj.min-distances :refer [min-distances]]
            [clojure.test :refer :all]))

(deftest min-distances-test
  (testing "[7 1 3 4 1 7]"
    (is (= 3 (min-distances [7 1 3 4 1 7]))))

  (testing "2 3 1 6 1 4"
    (is (= 2 (min-distances [2 3 1 6 1 4]))))

  (testing "1 2 3 4"
    (is (= -1 (min-distances [1 2 3 4])))))
