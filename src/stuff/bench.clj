(ns stuff.bench)

(defn bench [coll & args]
  "Returns a map of (coll x) -> elapsed with timing of applying args to (coll x)."
  (zipmap coll (map #(with-out-str (time (apply % args))) coll)))



  


