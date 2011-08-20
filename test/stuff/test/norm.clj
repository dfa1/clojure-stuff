(ns stuff.test.norm
  (:use [stuff.norm])
  (:use [clojure.test]))

(deftest test-with-zero-args[]
  (is (= (norm) 0)))

(deftest test-with-one-args[]
  (is (= (norm 2) 2)))

(deftest test-with-three-args[]
  (is (= (norm 3 4) 5)))

(deftest test-with-four-args[]
  (is (= (norm 3 3 3 3) 6)))

