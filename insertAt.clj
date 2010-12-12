(ns insert-at)

(defn insert-at [coll element position] 
  "Return a lazy collection with element at specific position."
  (concat
   (take position coll)
   (vector element)
   (drop position coll)))

(insert-at (range 10) "here" 5)
(insert-at (range 10) "here" 0)
(insert-at (range 10) "here" 10)

