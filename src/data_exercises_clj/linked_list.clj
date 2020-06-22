(ns data-exercises-clj.linked-list)

(defrecord Node [value next])

(defn has-cycle? [n]
  (when-let [next (:next n)]
    (loop [fast next
           slow (ref n)]
      (or (= fast slow)
          (when-let [next-fast (:next @fast)]
            (when-let [next-fast (:next @next-fast)]
              (recur next-fast (-> @slow :next))))))))

(comment
  ;; lower the print level to avoid stackoverflow
  (set! *print-level* 2)

  ;; simple linked list
  (def c (ref (->Node :c nil)))
  (def b (ref (->Node :b c)))
  (def a (->Node :a b))

  ;; no cycle
  (has-cycle? a)

  ;; update the list to be cyclic a -> b -> c -> b
  (dosync (ref-set c (->Node :c b)))
  (= (:next @c) b)

  ;; has cycle
  (has-cycle? a)

  ;; longer cycle
  ;; a -> b -> c -> d -> e -> b
  (def e (ref (->Node :e nil)))
  (def d (ref (->Node :d e)))
  (def c (ref (->Node :c d)))
  (def b (ref (->Node :b c)))

  (dosync (ref-set e (->Node :e b)))
  (def a (->Node :a b))

  (has-cycle? a)
)
