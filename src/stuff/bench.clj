(ns stuff.bench)

(defn update-min [n current new]
  (min current new))

(defn update-max [n current new]
  (max current new))

(defn update-avg [n current new]
  (+ current (/ new n)))

(defn bench-fn [n fn]
  "Returns the average of running fn repeat fn n times."
  (let [startTime (System/currentTimeMillis)]
    (doall (repeatedly n fn)) ; FIXME: dotimes
    (float (/ (- (System/currentTimeMillis) startTime) n))))

(defn bench-report [n fns]
  "Returns a map of fn->avg."
  (zipmap (keys fns) (map #(bench-fn n %) (vals fns))))
