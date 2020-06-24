(ns leetcode-clj.icecream-parlour)

(defn- idx
  "Index an array of integers, mapping each integer value to
  its 1-based positions in the array. Integers, which may
  occur multiple times, map to a list of positions,"
  [arr]
  (->> arr
       (map-indexed (fn [idx i] [(inc idx) i]))
       (reduce (fn [m [pos i]]
                 (if-let [positions (m i)]
                   (assoc m i (conj positions pos))
                   (assoc m i [pos])))
               {})))

(defn choose
  "The ice cream parlor problem. Given an amount of money m,
  select 2 different flavours, spending all the money."
  [prices m]
  (let [price-idx (idx prices)]
    (->> (map-indexed
          (fn [idx price]
            (let [pos (inc idx)
                  diff (- m price)]
              (when-let [diff-pos (-> (filter #(not= pos %)
                                              (price-idx diff))
                                      (first))]
                [pos diff-pos])))
          prices)
         (filter some?)
         (first))))

(comment
  (choose [1 4 5 3 2] 4)
  (choose [2 2 4 3] 4)
)
