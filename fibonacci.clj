(ns fibonacci)

(defn fibonacci
  "Classical recursive version"
  [n]

  (cond 
   (= n 0) 0
   (= n 1) 1 
    :else
    (+ (fibonacci (- n 1)) 
       (fibonacci (- n 2)))))

(defn tail-fibonacci
  "Tail fibonacci"
  [n]

  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (inner-fib next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn tail-fibonacci-improved
  "Tail fibonacci with `recur'"
  [n]

  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (recur next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn- next-pair [[v1 v2]]
  "Return the next Fibonacci pair"
  [v2 (+ v1 v2)])

(defn lazy-fibonacci-seq []
  "Return a lazy sequence of Fibonacci numbers."
  (map first (iterate next-pair [0 1])))

(defn lazy-fibonacci [n]
  (nth (lazy-fibonacci-seq) n))

;; benchmarks
(def algorithms
     [fibonacci tail-fibonacci tail-fibonacci-improved lazy-fibonacci])

(map #(time (% 20)) algorithms)
