(ns stuff.test.uppercase
  (:use [stuff.uppercase])
  (:use [clojure.test]))

(deftest yield-true-when-is-uppercase []
  (is (= true (uppercase? "UPPERCASE"))))

(deftest yield-true-when-is-uppercase-with-special-chars []
  (is (= true (uppercase? "UPPER_CASE!!"))))

(deftest yield-false-when-mixedcase []
  (is (= false (uppercase? "mixedCase"))))

(deftest yield-false-when-lowercase []
  (is (= false (uppercase? "lowercase"))))

(deftest yield-false-when-is-empty []
  (is (= false (uppercase? ""))))

(deftest yield-false-when-is-nil []
  (is (= false (uppercase? nil))))

