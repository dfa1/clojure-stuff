(ns stuff.uppercase)

(defn uppercase? [string]
  (let [characters (seq string)]
    (and
     (not (empty? characters))
     (every? #(Character/isUpperCase %) (filter #(Character/isLetter %) characters)))))

