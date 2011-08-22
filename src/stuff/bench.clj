(ns stuff.bench)

(defn bench-fn [n fn]
  "Returns the average of running fn repeat fn n times."
  (let [startTime (System/currentTimeMillis)]
    (doall (repeatedly n fn)) ; FIXME: dotimes
    (float (/ (- (System/currentTimeMillis) startTime) n))))

(defn bench-report [n fns]
  "Returns a map of fn->avg."
  (zipmap (keys fns) (map #(bench-fn n %) (vals fns))))

