(ns data-exercises-clj.queues)

;; implement a queue using 2 stacks
(defrecord Queue [stack1 stack2])

(defn queue [] (Queue. '() '()))

(defn enqueue [q v]
  (->> (conj (:stack1 q) v)
       (assoc q :stack1)))

(defn- transfer [source target]
  (if (seq source)
    (let [i (first source)]
      (recur (rest source) (conj target i)))
    target))

(defn dequeue [q]
  (if (seq (:stack2 q))
    (->> (rest (:stack2 q))
         (assoc q :stack2))
    (let [s2 (transfer (:stack1 q) (:stack2 q))]
      (-> (assoc q :stack2 (rest s2))
          (assoc :stack1 '())))))

(defn peek' [q]
  (if (seq (:stack2 q))
    (first (:stack2 q))
    (let [s2 (transfer (:stack1 q) (:stack2 q))]
      (first s2))))

(defn empty?' [q]
  (and (empty? (:stack1 q)) (empty? (:stack2 q))))

(comment
  (-> (queue)
      (enqueue 1)
      (enqueue 2)
      (enqueue 3)
      (dequeue)
      (enqueue 4)
      (dequeue)
      (peek'))

  (-> (queue)
      (dequeue)
      (dequeue)
      (enqueue 1)
      (dequeue)
      (empty?'))

  (-> (queue)
      (enqueue 2)
      (empty?'))

  ;; native queue
  (-> (clojure.lang.PersistentQueue/EMPTY)
      (conj 1)
      (conj 2)
      (conj 3)
      (pop))

  (-> (clojure.lang.PersistentQueue/EMPTY)
      (into [1 2 3 4]))

)
