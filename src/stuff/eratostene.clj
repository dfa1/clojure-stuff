(defn diffset  [s1 s2]
  (reduce #(disj %1 %2) s1 (list* s2)))

(defn multiples [n upTo]
  (set
   (take upTo (iterate #(+ n)))))

(defn eratostene [n]
  (sort
   (reduce diffset (map #(multiples % n) (range 1 (inc n))))))
  
(eratostene 100)


