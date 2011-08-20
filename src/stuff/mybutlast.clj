(ns stuff.mybutlast)

(defn mybutlast [coll]
  "A naive (and slow) implementation of butlast."
  (first (partition (dec (count coll)) coll)))
