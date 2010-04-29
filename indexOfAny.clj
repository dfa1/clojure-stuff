;; same example of "Programming Clojure" (page 51), different code 
;; http://commons.apache.org/lang/api/org/apache/commons/lang/StringUtils.html
;;
;; A null String will return -1. A null or zero length search array
;; will return -1.
;;
;;  StringUtils.indexOfAny(null, *)                = -1
;;  StringUtils.indexOfAny("", *)                  = -1
;;  StringUtils.indexOfAny(*, null)                = -1
;;  StringUtils.indexOfAny(*, [])                  = -1
;;  StringUtils.indexOfAny("zzabyycdxx",['z','a']) = 0
;;  StringUtils.indexOfAny("zzabyycdxx",['b','y']) = 3
;;  StringUtils.indexOfAny("aba", ['z'])           = -1

(ns examples)

(defn index-of [str ch & others] 
  (let [keys (set (conj others ch))] 
    (map str keys)
    ))

(index-of "aaa" \a \b \c \d \e)
(set [1 2 3 4])

(defn- str-to-map

