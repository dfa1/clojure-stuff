(ns stuff.test.taylor
  (:use [stuff.taylor])
  (:use [clojure.test]))

(deftest test-powers-of []
  (let [powers-of-two (take 10 (powers-of 2))]
    (is (= 1024 (last powers-of-two)))))

(deftest test-factorials []
  (let [first-five-factorials (take 5 (factorials))]
    (is (= 120 (last first-five-factorials)))))

(deftest test-first-alternate-sign-yields-arguments []
  (is (= 5 (first (take 10 (alternate-signs 5))))))

(deftest test-alternate-sign []
  (is (= [-1 1 -1 1] (vec (take 4 (alternate-signs -1))))))

(run-tests)

