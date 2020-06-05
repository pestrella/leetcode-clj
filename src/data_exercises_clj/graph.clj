(ns data-exercises-clj.graph)

(defn has-path? [graph start end]
  (loop [nodes (conj clojure.lang.PersistentQueue/EMPTY [start])
         visited #{}]
    (when-let [[node & path] (peek nodes)]
      (if (= node end)
        (conj path node)
        (recur (->> (graph node)
                    (filter #(not (visited %)))
                    (map #(conj path node %))
                    (apply conj (pop nodes)))
               (conj visited node))))))

(comment
  ;; A - B - C
  (def graph {:a [:b]
              :b [:a :c]
              :c [:b]})

  (has-path? graph :a :d)

  ;; A - B - C - F
  ;; |       |
  ;; D - E   G
  ;; |   |   |
  ;; H   I - J
  (def graph {:a [:b :d]
              :b [:a :c]
              :c [:b :f :g]
              :d [:a :e :h]
              :e [:d :i]
              :f [:c]
              :g [:c :j]
              :h [:d]
              :i [:e :j]
              :j [:g :i]})

  (has-path? graph :a :j)
)
