(ns leetcode-clj.roman-numerals-test
  (:require [leetcode-clj.roman-numerals :refer [roman->int]]
            [clojure.test :refer :all]))

(deftest roman-numerals-to-int
  (testing "III"
    (is (= 3 (roman->int "III"))))

  (testing "IV"
    (is (= 4 (roman->int "IV"))))

  (testing "IX"
    (is (= 9 (roman->int "IX"))))

  (testing "LVIII"
    (is (= 58 (roman->int "LVIII"))))

  (testing "MCMXCIV"
    (is (= 1994 (roman->int "MCMXCIV")))))
