(ns data-exercises-clj.word-area)

;; solution to https://www.hackerrank.com/challenges/designer-pdf-viewer/problem

(def alphabet (map char (range (int \a) (inc (int \z)))))

(defn word-area [heights word]
  (let [heights (->> (map vector alphabet heights)
                     (into {}))]
    (->> (map heights word)
         (apply max)
         (* (count word)))))

;; faster
(defn word-area' [heights word]
  (->> (map #(heights (- (int %) 97)) word)
       (apply max)
       (* (count word))))

(comment
  (word-area' [1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 7] "zaba")
)
