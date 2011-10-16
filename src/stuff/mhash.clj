(ns stuff.mhash)

;; simulate m-hash functions (see double hashing)

(defn mhash [m f g]
  "Returns a function that simulates m hash functions by having two
 independent hash functions `f` and `g`. This can be as simple as this
 function to create the ith hash of a key, given the results a and b
 of hashing a key with these two functions:

      hash[i] = a + b * i, where 1 <= i <= m 

 This can be useful in bloom filters and related structures."
  (fn [x]
    (let [a (f x) b (g x)]
      (assert (not (= a b)) "hash functions cannot yield the same value")
      (map #(+ a (* b %)) (range 1 (inc m))))))

;; default-mhash
(defn identity-hashcode [x]
  (System/identityHashCode x))

(defn hashcode [x]
  (.hashCode x))

(defn default-mhash [m]
  "mhash factory that uses identity-hashcode and instance hashcode."
  (mhash m identity-hashcode hashcode))

((default-mhash 5) "hello mhash!")
((default-mhash 10) "hello mhash!")



