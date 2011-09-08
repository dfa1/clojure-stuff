(ns stuff.fibonacci)

(defn recursive-fibonacci [n]
  "Classical recursive version."

  (cond 
   (= n 0) 0
   (= n 1) 1 
    :else
    (+ (recursive-fibonacci (- n 1)) 
       (recursive-fibonacci (- n 2)))))

(defn tail-fibonacci [n]
  "Tail fibonacci."

  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (inner-fib next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn tail-fibonacci-improved [n]
  "Tail fibonacci with 'recur'."
  
  (letfn [(inner-fib [current next n] 
    (if (zero? n) 
      current
      (recur next (+ current next) (- n 1))))]
    (inner-fib 0 1 n)))

(defn next-pair [[v1 v2]]
  "Return the next Fibonacci pair."
  [v2 (+ v1 v2)])

(defn lazy-fibonacci-seq []
  "Return a lazy sequence of Fibonacci numbers."
  (map first (iterate next-pair [0 1])))

(defn lazy-fibonacci [n]
  "Return the nth fibonacci number."
  (nth (lazy-fibonacci-seq) n))


(defmulti multimethod-fibonacci int)
(defmethod multimethod-fibonacci 0 [n] 0)
(defmethod multimethod-fibonacci 1 [n] 1)
(defmethod multimethod-fibonacci :default [n]
  (let [fib multimethod-fibonacci]
    (+ (fib (- n 2)) (fib (- n 1)))))

(defn multi-fibonacci [n]
  "Multimethod based fibonacci.
  XXX: work around missing meta :name for defmulti functions"
  (multimethod-fibonacci n))
