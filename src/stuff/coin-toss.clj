; random coin toss results
(defn lazy-random [max]
  (repeatedly #(rand-int max)))

(partition 4 (take 10 (lazy-random 2)))

(take 10 (lazy-random 6))

(defn- toss [n]
  (cond
   (= n 0) :head
   (= n 1) :crux))

(toss 1)

(defn tosses [n]
  (map toss (take n (lazy-random 2))))

(defn two-heads [v]
  (every? #(= % :head) v))

(two-heads [:head :head])
(two-heads [:head :crux])

(count (filter two-heads (partition 2 1 (tosses 20))))
