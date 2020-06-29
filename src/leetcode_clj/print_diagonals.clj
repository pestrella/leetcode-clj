(ns leetcode-clj.print-diagonals)

(defn- inbounds? [m [row col]]
  (and (< -1 row (count m))
       (< -1 col (count (m 0)))))

(defn print-diag [m [row col]]
  (loop [[row col] [row col]]
    (when (inbounds? m [row col])
      (print (get-in m [row col]))
      (recur [(inc row) (dec col)]))))

(defn print-diags [m]
  (for [row (range (count m))
        col (range (count (m 0)))]
    (do (print-diag m [row col])
        (println))))

(comment
  (print-diags [[1 2  3  4]
                [5 6  7  8]
                [9 10 11 12]])
)
