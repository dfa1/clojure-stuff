(ns stuff.uppercase)

(defn uppercase? [string]
  "Returns true if every letter in string is uppercase, else false."
  (let [characters (seq string)]
    (and
     (every? #(Character/isUpperCase %) (filter #(Character/isLetter %) characters)))))

