(ns stuff.errors)

(defmacro ignore-errors [forms]
  `(try
     (~@forms)
    (catch java.lang.Throwable e#)))

(ignore-errors
 (throw (new AssertionError)))


