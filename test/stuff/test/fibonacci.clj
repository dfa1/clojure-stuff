(ns stuff.test.fibonacci
  (:use [stuff.fibonacci])
  (:use [stuff.bench])
  (:use [clojure.test]))

(defn- extract-fn-names [fns]
  (map #(:name (meta  %)) fns))

(defn- bind-params [fns]
  (map #(partial % 10) fns))

(defn- prepare-functions [fns]
  (zipmap
   (extract-fn-names fns)
   (bind-params fns)))

(deftest test-benchmarks []
  (doall
   (map println (bench-report 1000
                              (prepare-functions [recursive-fibonacci tail-fibonacci tail-fibonacci-improved lazy-fibonacci multi-fibonacci])))))


