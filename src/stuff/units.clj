;; units library 
;; inspired by Let Over Lambda example at page 112

(def *units* {
              'us '(1/1000 ms)
              'ms '(1/1000 s)
              'm '(60 s) 
              'h '(60 m)
              'd '(24 h)
              })

(defn next-unit [old-unit new-unit]
  (list (* (first old-unit) (first new-unit)) (second new-unit)))

(next-unit '(2 m) '(60 s))

(defn convert [value]
  (let [new-unit (second value)]
    (if (not (contains? *units* new-unit))
    value
    (recur (next-unit value (get *units* new-unit))))))

(convert '(3000 ms))
(convert '(3000000 us))
(convert '(3 m))

;; TODO:
;;   - bidirectional conversion
;;   - detect recursive definitions
;;   - example with kilobytes, mega, etc
;;   - defunit macro:
;; (defunit time s
;;   m (60 s)
;;   h (60 m)
;;   d (24 h)
;;   ms (1 / 1000 s)
;;   us (1 / 1000 ms))




