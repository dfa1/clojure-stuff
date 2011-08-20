(ns stuff.test.norm
  (:use [stuff.norm])
  (:use [clojure.test]))

(deftest test-with-zero-args[]
  (is (= 0 (norm))))

(deftest test-with-one-args[]
  (is (= 2 (norm 2))))

(deftest test-with-three-args[]
  (is (= 5 (norm 3 4))))

(deftest test-with-four-args[]
  (is (= 6 (norm 3 3 3 3))))

