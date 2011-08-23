(ns stuff.fibonacci
  (:use [stuff.bench]))

(defn fibonacci [n]
  "Classical recursive version"

  (cond 
   (= n 0) 0
   (= n 1) 1 
    :else
    (+ (fibonacci (- n 1)) 
       (fibonacci (- n 2)))))

(defn tail-fibonacci [n]
  "Tail fibonacci"

  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (inner-fib next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn tail-fibonacci-improved [n]
  "Tail fibonacci with `recur'"
  
  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (recur next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn- next-pair [[v1 v2]]
  "Return the next Fibonacci pair"
  [v2 (+ v1 v2)])

(defn lazy-fibonacci-seq []
  "Return a lazy sequence of Fibonacci numbers."
  (map first (iterate next-pair [0 1])))

(defn lazy-fibonacci [n]
  (nth (lazy-fibonacci-seq) n))


(defmulti multimethod-fibonacci int)
(defmethod multimethod-fibonacci 0 [n] 0)
(defmethod multimethod-fibonacci 1 [n] 1)
(defmethod multimethod-fibonacci :default [n]
  (let [fib multimethod-fibonacci]
    (+ (fib (- n 2)) (fib (- n 1)))))

; benchmarks
(defn extract-fn-names [fns]
  (map #(:name (meta %)) fns))

(defn bind-params [fns]
  (map #(partial % 10) fns))

(defn prepare-functions [fns]
  (zipmap
   (extract-fn-names fns)
   (bind-params fns)))

(def *functions* [fibonacci tail-fibonacci tail-fibonacci-improved lazy-fibonacci multimethod-fibonacci])
(bench-report 1000 (prepare-functions *functions*))

