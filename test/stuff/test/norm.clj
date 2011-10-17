(ns stuff.test.norm
  (:use [stuff.norm])
  (:use [clojure.test]))

(deftest test-with-zero-args
  (is (= 0.0 (norm))))

(deftest test-with-one-args
  (is (= 1.0 (norm 1))))

(deftest test-with-two-args
  (is (= 2.23606797749979 (norm 1 2))))


