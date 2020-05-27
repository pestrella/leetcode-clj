(ns data-exercises-clj.rotation)

(defn rotate
  "Rotate array in the specified direction `dir`. Negative
  direction is left, positive right"
  [arr dir]
  (let [len (count arr)
        new-pos (mod dir len)
        [front back] (split-at (- len new-pos) arr)]
    (concat back front)))

(comment
  (rotate [1 2 3 4 5] -2)
  (rotate [1 2 3 4 5] 4)
)
