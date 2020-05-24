(ns data-exercises-clj.sorting)

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
