(ns stuff.mybutlast)

(defn mybutlast [coll]
  "A naive (and slow) implementation of butlast."
  (first (partition (dec (count coll)) coll)))

(defn mybutlast2 [coll]
  (if (= (count coll) 1) ()
      (cons (first coll) (mybutlast2 (rest coll)))))

(defn mybutlast3 [coll]
  (reverse (rest (reverse coll))))

(defn mybutlast4 [coll]
  (-> coll reverse rest reverse))




