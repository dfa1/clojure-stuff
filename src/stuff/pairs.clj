(ns stuff.couples)

;; http://swizec.com/blog/functional-isnt-always-better/swizec/2591
;;
;; The problem is one of turning a list of values, say, [A, B, C, D]
;; into a list of pairs with itself, like so [[A,B], [A,C], [A,D], [B,
;; C], [B,D], [C,D]].

(defn pairs [values]
   (for [x values y values :when (< x y) ] [x y]))

(assert (pairs [1 2 3 4]) ([1 2] [1 3] [1 4] [2 3] [2 4] [3 4]))

