(ns stuff.norm)

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
  (-> values squares sum Math/sqrt))


