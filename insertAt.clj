(ns insert-at)

(defn insert-at [coll element position] 
  (concat (take position coll) [element] (drop position coll)))

(insert-at (range 10) -1 5)
