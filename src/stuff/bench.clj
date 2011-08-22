(ns stuff.bench)

(defn bench-fn [n fn]
  (let [startTime (System/currentTimeMillis)]
    (doall (repeatedly n fn)) ; FIXME: dotimes
    (float (/ (- (System/currentTimeMillis) startTime) n))))

(defn bench-report [n fns]
  "Returns a map of (coll x) -> elapsed with timing of applying args to (coll x)."
  (zipmap (keys fns) (map #(bench-fn n %) (vals fns))))






  
