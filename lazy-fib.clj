;; lazy fib
(defn- next-fib-pair [[v1 v2]]
  "Return the next fib pair"
  [v2 (+ v1 v2)])

(defn lazy-fib []
  "Return a lazy sequence of Fibonacci numbers."
  (map first (iterate next-fib-pair [0 1])))

;; tests
(next-fib-pair [10 11])
(take 10 (lazy-fib))
(nth (lazy-fib) 1011)


