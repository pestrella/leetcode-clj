(ns data-exercises-clj.graph)

(defrecord Node [name destinations])

(defn has-path? [node name]
  (loop [nodes [node]
         visited #{}]
    (when-let [node (first nodes)]
      (if (= name (:name @node))
        node
        (recur (->> (:destinations @node)
                    (filter #(not (visited (:name (deref %))))))
               (conj visited (:name @node)))))))

(comment
  ;; A - B - C
  (def a (ref (->Node :a [])))
  (def b (ref (->Node :b [])))
  (def c (ref (->Node :c [])))

  ;; set *print-level* when working in a REPL to avoid stackoverflow
  ;; when any cyclic references are printed
  (set! *print-level* 2)
  (dosync (alter a assoc :destinations [b])
          (alter b assoc :destinations [a c])
          (alter c assoc :destinations [b]))

  (has-path? a :c)
)
