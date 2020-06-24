(ns leetcode-clj.fibonacci)

(defn fib [n]
  (condp = n
    0 0
    1 1
    (+ (fib (dec n)) (fib (- n 2)))))

(defn fib-seq [n]
  (loop [i 0
         s []]
    (if (< i n)
      (recur (inc i) (conj s (fib i)))
      s)))

(defn fib-seq'
  "Lazy sequence fibonacci"
  ([] (fib-seq' 0 1))
  ([a b] (cons a (lazy-seq (fib-seq' b (+ a b))))))

(defn fib'
  "Fibonacci using laziness"
  [n]
  (nth (fib-seq') n))

(comment
  (fib-seq 10)
  (take 10 (fib-seq'))
  (time (fib 25))
  (time (fib' 25))
)
