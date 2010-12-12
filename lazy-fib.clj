; my fib
(defn- next-pair [[v1 v2]]
  [v2 (+ v1 v2)])

(next-pair [10 11])

(last (take 5 (iterate next-pair [0 1])))

(defn lazy-fib []
  (iterate next-pair [0 1]))

(take 10 (lazy-fib))
