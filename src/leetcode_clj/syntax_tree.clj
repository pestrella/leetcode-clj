(ns leetcode-clj.syntax-tree)

;; Given input like "4+2*3", where only multiplication
;; and addition operators are used, output the answer 10

(defn syntax-tree [s]
  (loop [multiply []
         add []
         s s]
    (if-let [c (first s)]
      (condp = c
        \+ (recur [] (conj add multiply) (rest s))
        \* (recur multiply add (rest s))
        (recur (conj multiply (- (int c) 48)) add (rest s)))
      (if (seq multiply)
        (conj add multiply)
        add))))

(defn calc [s]
  (->> (syntax-tree s)
       (map #(apply * %))
       (apply +)))
