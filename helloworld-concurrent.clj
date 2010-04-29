(def visitors (ref #{}))

(defn hello [username]
  (dosync 
     (if-not (@visitors username)
       (do
	 (alter visitors conj username)
	 (str "Hello " username))
       (do
	 (str "Welcome back " username)))))

(hello "Davide") ;; returns "Hello Davide"
(hello "Davide") ;; returns "Welcome back Davide"



