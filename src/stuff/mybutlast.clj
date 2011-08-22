(ns stuff.mybutlast
  (:use [stuff.bench]))

(defn butlast-partition [coll]
  "A naive (and slow) implementation of butlast."
  (first (partition (dec (count coll)) coll)))

(defn butlast-recursive [coll]
  "A recursive implementation."
  (if (= (count coll) 1) ()
      (cons (first coll) (mybutlast2 (rest coll)))))

(defn butlast-reverse [coll]
  "A reverse based implementation."
  (reverse (rest (reverse coll))))

(defn butlast-reverse-> [coll]
  "Implemented with reverse and ->."
  (-> coll reverse rest reverse))

(defn butlast-vec-pop [coll]
  "Karrie rocks."
  (pop (vec coll)))

;; simple benchmark
(def *functions* [butlast-partition butlast-recursive butlast-reverse butlast-reverse-> butlast-vec-pop])
(def functions-to-benchmark
  (zipmap
   (map #(:name (meta %)) *functions*)
   (map #(partial % (range 100)) *functions*)))
(bench-report 1000 functions-to-benchmark) 
