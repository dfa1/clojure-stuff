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
    (some (fn [v] (contains? keys v)) (seq str)) 
    ))

(index-of "aaa"  \b \c \d \e)

(defn indexed [coll] (map  coll))

(indexed "abcd")
