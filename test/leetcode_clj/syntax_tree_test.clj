(ns leetcode-clj.syntax-tree-test
  (:require [leetcode-clj.syntax-tree :refer [calc]]
            [clojure.test :refer :all]))

(deftest simple-syntax
  (testing "simple syntax: 4+2*3"
    (is (= (+ 4 (* 2 3)) (calc "4+2*3"))))

  (testing "simple syntax: 2*3+4+5*6*9"
    (is (= (+ (* 2 3) 4 (* 5 6 9)) (calc "2*3+4+5*6*9"))))

  (testing "simplet syntax: 1+1+2"
    (is (= (+ 1 1 2) (calc "1+1+2"))))

  (testing "simple syntax: 3*4*2"
    (is (= (* 3 4 2) (calc "3*4*2")))))
