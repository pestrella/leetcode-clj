(ns leetcode-clj.diagonals)

;; Print the diagonals of a matrix, e.g.
;; input: [[1 2 3]
;;         [4 5 6]
;;         [7 8 9]]
;; print:
;; 1
;; 2 4
;; 3 5 7
;; 6 8
;; 9

(defn- inbounds? [m [row col]]
  (and (< -1 row (count m))
       (< -1 col (count (m 0)))))

(defn get-diagonal [m [row col]]
  (loop [[row col] [row col]
         diag []]
    (if (inbounds? m [row col])
      (recur [(inc row) (dec col)]
             (conj diag (get-in m [row col])))
      diag)))

(defn diagonals [m]
  (let [height (count m)
        width (count (m 0))]
    (loop [row 0
           col 0
           diags []]
      (if (< row height)
        (let [diags (->> (for [col (range col width)]
                           (get-diagonal m [row col]))
                         (apply conj diags))]
          (recur (inc row) (dec width) diags))
        diags))))

(defn print-diagonals [m]
  (doseq [diag (diagonals m)]
    (doseq [n diag]
      (print (str n " ")))
    (println)))

(comment
  (print-diagonals [[1  2  3  4]
                    [5  6  7  8]
                    [9  10 11 12]])
)
