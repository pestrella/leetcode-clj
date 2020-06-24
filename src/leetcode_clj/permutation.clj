(ns leetcode-clj.permutation)

(defn- swap
  "swap item at index i with j of the given array"
  [arr i j]
  (-> (assoc arr i (arr j))
      (assoc j (arr i))))

(defn- permutation' [arr n]
  (if (= 1 n)
    arr
    (loop [perms []
           arr arr
           i 0]
      (if (< i n)
        (let [arr' (swap arr i (dec n))]
          (recur (into perms (permutation' arr' (dec n)))
                 arr
                 (inc i)))
        perms))))

(defn permutation [arr]
  (let [n (count arr)]
    (->> (permutation' arr n)
         (partition n))))

(comment
  (swap [1 2 3] 1 2)
  (permutation [1 2 3])
  (permutation [1 2])
)
