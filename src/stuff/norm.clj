(ns norm)

(use 'clojure.test)
(import java.lang.Math)

(defn square [n]
  "Return the square of a number."
  (* n n))

(defn squares [coll]
  "Return a lazy seq whose values are the square of coll."
  (map square coll))

(defn sum [coll]
  "Return the sum of the values of coll."
  (reduce + coll))

(defn norm [& values]
  "Return the Euclidean distance between points."
  (Math/sqrt (sum (squares values))))

(is (= (norm) 0))
(is (= (norm 2) 2))
(is (= (norm 3 4) 5))
(is (= (norm 3 3 3 3) 6))

