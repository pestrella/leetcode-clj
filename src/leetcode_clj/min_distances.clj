(ns leetcode-clj.min-distances)

;; Find the minimum distance between any pair of equal
;; elements in an array, e.g
;; input: [7 1 3 4 1 7]
;; output: 3 (distance between the 1s)

(defn min-distances [arr]
  (loop [i 0
         visited {}
         solution nil]
    (if (< i (count arr))
      (let [n (arr i)]
        (if-let [i' (visited n)]
          (let [dist (- i i')]
            (if (or (nil? solution) (< dist solution))
              (recur (inc i) visited dist)
              (recur (inc i) visited solution)))
          (recur (inc i) (assoc visited n i) solution)))
      (or solution -1))))
