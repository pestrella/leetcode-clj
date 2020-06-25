(ns leetcode-clj.two-sum)

(defn two-sum
  "Given an array of integers, returns 2 numbers that add up to the
  target. Does not use the same elements twice."
  [nums target]
  (loop [i 0
         prev-nums {}]
    (when (< i (count nums))
      (let [n (nums i)
            diff (- target n)]
        (if-let [indexes (prev-nums diff)]
          (->> (filter #(not= i %) indexes)
               (first)
               (conj (list i)))
          (let [indexes (or (prev-nums n) [])]
            (recur (inc i) (assoc prev-nums n (conj indexes i)))))))))

(comment
  (two-sum [2 2 4 3] 4)
  (two-sum [1 4 5 3 2] 4)
)
