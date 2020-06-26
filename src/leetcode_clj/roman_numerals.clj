(ns leetcode-clj.roman-numerals)

(def roman {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})

(defn roman->int [s]
  (loop [numerals (seq s)
         total 0]
    (if (seq numerals)
      (let [numeral (first numerals)]
        (if-let [next (first (rest numerals))]
          (if (< (roman numeral) (roman next))
            (recur (rest numerals) (- total (roman numeral)))
            (recur (rest numerals) (+ total (roman numeral))))
          (recur (rest numerals) (+ total (roman numeral)))))
      total)))
