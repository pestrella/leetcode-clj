(ns leetcode-clj.rotation)

(defn rotate
  "Rotate array in the specified direction `dir`. Negative
  direction is left, positive right"
  [arr dir]
  (let [len (count arr)
        new-pos (mod dir len)
        [front back] (split-at (- len new-pos) arr)]
    (concat back front)))

(defn gcd
  "Return the greater common divisor."
  [a b]
  (if (= 0 b)
    a
    (recur b (mod a b))))

(comment
  (split-at 2 [1 2 3 4 5])
  (rotate [1 2 3 4 5] -2)
  (rotate [1 2 3 4 5] 4)
)
