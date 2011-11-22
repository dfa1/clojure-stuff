(use 'clojure.set)

(defn multiples [n upTo]
  (set
   (take upTo (iterate #(+ n)))))

(defn eratostene [n]
  (sort
   (reduce difference (map #(multiples % n) (range 1 (inc n))))))
  
(eratostene 100)
