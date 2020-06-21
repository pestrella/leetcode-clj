(ns data-exercises-clj.binary-tree)

(defrecord Node [data left right])

(defn insert [node value]
  (if node
    (if (< value (:data node))
      (assoc node :left (insert (:left node) value))
      (assoc node :right (insert (:right node) value)))
    (Node. value nil nil)))

(defn- str' [node]
  (str
   (if (:left node) (str' (:left node)) "")
   (:data node) " "
   (if (:right node) (str' (:right node)) "")))

(defn print-data [node]
  (println (str' node)))

(defn assoc-data
  ([node] (assoc-data {} node 0))
  ([data node depth]
   (if node
     (let [values (or (data depth) [])]
       (-> (assoc data depth (conj values (:data node)))
           (assoc-data (:left node) (inc depth))
           (assoc-data (:right node) (inc depth))))
     data)))

;; get the average of all the numbers at each depth of the tree
(defn get-averages [node]
  (let [depths (assoc-data node)]
    (map #(let [[_ v] %]
            (-> (apply + v)
                (/ (count v))
                (float)))
         depths)))

(defn get-height
  ([node] (get-height node -1))
  ([node depth]
   (if node
     (max (get-height (:left node) (inc depth))
          (get-height (:right node) (inc depth)))
     depth)))

(comment
  ;;      8
  ;;     / \
  ;;    /   \
  ;;   4    15
  ;;  / \   / \
  ;; 2   6 9  22
  ;;    /    /
  ;;   5    17

  (def n (-> (Node. 8 nil nil)
             (insert 4)
             (insert 15)
             (insert 22)
             (insert 9)
             (insert 17)
             (insert 6)
             (insert 2)
             (insert 5)))

  (get-averages n)
  (get-height n)
)
