;; doctests
;; inspired by python
(ns stuff.doctest)

(defn sum [ & args] 
  """
user=> (sum)
0
user=> (sum 1)
1
user=> (sum 1 2 3)
1 2 3
  """
  (reduce + args))




