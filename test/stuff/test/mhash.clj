(ns stuff.test.mhash
  (:use [stuff.mhash])
  (:use [clojure.test]))

(deftest requires-m-greater-than-1
  (is (thrown? AssertionError
               (mhash 0 identity-hashcode instance-hashcode))))

(deftest requires-two-indipendent-hash-functions
  (is (thrown? AssertionError
               ((mhash 1 identity-hashcode identity-hashcode) "hello!"))))

(deftest requires-non-nil-argument
  (is (thrown? AssertionError
               ((mhash 1 identity-hashcode instance-hashcode) nil))))

(deftest yields-requested-number-of-hash-functions
  (let [m 3]
    (is (= (count
            ((mhash m identity-hashcode instance-hashcode) "hello!")) m))))

