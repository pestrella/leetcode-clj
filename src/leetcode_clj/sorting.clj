(ns leetcode-clj.sorting)

(defn- merge' [comp left right]
  (loop [sorted []
         l left
         r right]
    (if (and (seq l) (seq r))
      (if (apply comp [(first l) (first r)])
        (recur (conj sorted (first l)) (rest l) r)
        (recur (conj sorted (first r)) l (rest r)))
      (concat sorted l r))))

;; TODO: makes this faster by sorting sublists concurrently.
;; Think about bufferring tasks to avoid too many threads.
(defn mergesort [comp coll]
  (if (> (count coll) 1)
    (->> (split-at (/ (count coll) 2) coll)
         (map #(mergesort comp %))
         (apply merge' comp))
    coll))

(defn- insert'[comp arr i]
  (if (= i 0)
    arr
    (let [j (dec i)
          curr (get arr i)
          prev (get arr j)]
      (if (comp curr prev)
        (recur comp
               (-> arr
                   (assoc j curr)
                   (assoc i prev))
               j)
        arr))))

(defn insertion-sort [comp arr]
  (loop [i 0
         arr arr]
    (if (< i (count arr))
      (recur (inc i)
             (insert' comp arr i))
      arr)))

(defn- pivot-compare [comp arr i pivot step]
  (loop [i i]
    (if (comp (get arr i) pivot)
      (recur (+ i step))
      i)))

(defn- swap
  [arr i j]
  (-> arr
      (assoc i (arr j))
      (assoc j (arr i))))

(defn- partition' [pred arr lo hi]
  (let [pivot (get arr (-> (+ lo hi)
                           (/ 2)
                           (int)))]
    (loop [arr arr
           i lo
           j hi]
      (let [i (pivot-compare pred arr i pivot 1)
            j (pivot-compare (comp not pred) arr j pivot -1)]
        (if (>= i j)
          [arr j]
          (recur (swap arr i j) (inc i) (dec j)))))))

(defn- quick-sort' [arr pred lo hi]
  (if (< lo hi)
    (let [[arr border] (partition' pred arr lo hi)]
      (-> arr
          (quick-sort' pred lo border)
          (quick-sort' pred (inc border) hi)))
    arr))

(defn quick-sort [pred arr]
  (quick-sort' arr pred 0 (dec (count arr))))

(comment
  (insertion-sort < [1 4 3 5 6 2])

  (quick-sort < [1 4 2 3 5 6 2])
)
