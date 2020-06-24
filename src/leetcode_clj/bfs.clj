(ns leetcode-clj.bfs
  (:require [leetcode-clj.binary-tree :refer [->Node insert print-data]]))

;; compare this bfs implementation with the dfs version in binary_tree.clj

(defn assoc-data
  ([node] (assoc-data {} [node] 0))
  ([data nodes depth]
   (if (seq nodes)
     (let [[curr-depth-data next-depth-nodes]
           (reduce (fn [[data next-depth-nodes] node]
                     [(conj data (:data node))
                      (-> (filter some? [(:left node) (:right node)])
                          (into next-depth-nodes))])
                   [[] []]
                   nodes)]
       (recur (assoc data depth curr-depth-data)
              next-depth-nodes
              (inc depth)))
     data)))

(defn get-averages [node]
  (->> (assoc-data node)
       (map #(let [[_ v] %]
               (-> (apply + v)
                   (/ (count v))
                   (float))))))

(defn get-height [node]
  (loop [nodes [node]
         depth -1]
    (if (seq nodes)
      (recur (reduce (fn [next-depth-nodes node]
                       (-> (filter some? [(:left node) (:right node)])
                           (into next-depth-nodes)))
                     []
                     nodes)
             (inc depth))
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

  (def n (-> (->Node 8 nil nil)
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
