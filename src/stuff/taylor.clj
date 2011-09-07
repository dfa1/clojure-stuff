(ns stuff.taylor)

(defn powers-of [x]
  "Returns a lazy sequence of powers of x."
  (iterate #(* % x) x))

(defn factorial-seq [n fact]
  (let [n+1 (inc n)]
    (lazy-seq
     (cons fact (factorial-seq n+1 (* n+1 fact))))))

(defn factorials []
  "Returns a lazy sequence of factorials numbers."
  (factorial-seq 1 1))

(defn alternate-signs [x]
  "Returns an infinite lazy sequence of x, -x, x, -x..."
  (iterate #(* % -1) x))

(defn taylor [nterms]
  "Returns a lazy taylor serie of terms."
  (throw (new UnsupportedOperationException)))



