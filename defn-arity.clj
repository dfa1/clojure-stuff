(ns examples)

(import java.lang.Math)

(defn norm
  "the Euclidean distance, defined by cases"
  ([n] n)
  ([n & others] (Math/sqrt (reduce + (map #(* % %) (conj others n))))))

(norm 2)
(norm 3 4)

