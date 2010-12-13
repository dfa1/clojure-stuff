;; inspired by "Programming Clojure" (page 31)
(ns crypt)

(defn crypt [message key]
  "crypt message with key"
  (apply str (interleave message key)))

(defn decrypt [message]
  (apply str (take-nth 2 message)))

(crypt "a plain text message" "a key that must be longer than the message")

(decrypt (crypt "a plain text message" "a key that must be longer than the message"))
