;; doctests
;; inspired by python
(ns stuff.doctest)

(defn sum  
  "
user=> (sum)
0
user=> (sum 1)
1
user=> (sum 1 2 3)
6
"
  [ & args]
  (reduce + args))

(defn samples [s]
  (map seq (map #(.split % "\n") (remove empty? (re-seq #"user=>.*\n.*" s)))))

(defn dotest [[input, expected]]
  (let [test (.replace input "user=>" "")
        actual (eval (read-string test)) ]
    (assert
     (=
      (eval (read-string expected))
      actual)
     (format "failed '%s' expected: %s got: %s" test expected actual))))

(defmacro docstring [f]
  `(doseq [sample# (samples (:doc (meta (var ~f))))]
     (dotest sample#)))

(docstring sum)



