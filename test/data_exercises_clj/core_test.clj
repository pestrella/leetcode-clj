(ns data-exercises-clj.core-test
  (:require [clojure.test :refer :all]
            [data-exercises-clj.sorting :refer :all]))

;; some merge sort performances measurements

(defn- get-players
  ([] (get-players 1))
  ([i] (lazy-seq (cons {:name (str "player_" i)
                        :score (int (rand 100))}
                       (get-players (inc i))))))

(deftest do-mergesort
  (testing "Do merge sort"
    (let [size 100000
          players (->> (get-players) (take size))
          sorted (time (mergesort #(> (:score %1) (:score %2))
                                   players))]
      (is (= size (count sorted))))))

(deftest simple-native-sort
  (testing "Sort using native sort"
    (let [players [{:name "louis" :score 41}
                   {:name "louis" :score 41}]
          sorted (sort #(> (:score %1) (:score %2))
                       players)]
      (is (= 2 (count players))))))

(deftest do-simple-sorted-set
  (testing "Simple sorted set"
    (let [players (sorted-set-by #(compare [(:score %2) (:name %1)]
                                           [(:score %1) (:name %2)])
                                 {:name "louis" :score 41}
                                 {:name "ricky" :score 67}
                                 {:name "dave" :score 92}
                                 {:name "ricky" :score 67}
                                 {:name "jerry" :score 85})]

      (is (= 5 (count players)))
      (println players))))

(deftest do-sorted-set
  (testing "Sorted set"
    (let [size 1000
          players (apply sorted-set-by
                         #(compare [(:score %2) (:name %1)]
                                   [(:score %1) (:name %2)])
                         (->> (get-players) (take size)))]
      (is (= size (count players))))))

(defn- random-numbers [n]
  (cons (int (rand n)) (lazy-seq (random-numbers n))))

(deftest do-quicksort
  (testing "Do quicksort"
    (let [size 10000
          numbers (into [] (take size (random-numbers 100000)))
          sorted (time (quick-sort numbers))]
      (is (= size (count sorted))))))

(comment
  (time (let [s (sort #(> (:score %1) (:score %2))
                      (->> (get-players) (take 1000)))]
          (println s)))

  (time (let [s (mergesort #(> (:score %1) (:score %2))
                           (->> (get-players) (take 100000)))]))

  ;; use a sorted-set
  (let [players (sorted-set-by
                 #(> (:score %1) (:score %2))
                 (->> (get-players) (take 1000)))])
)
