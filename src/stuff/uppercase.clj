(ns stuff.uppercase)

(defn uppercase? [string]
  "Returns true if every letter in string is uppercase, else false."
  (and
   (not (empty? string))
   (every? #(Character/isUpperCase %) (filter #(Character/isLetter %) string))))


