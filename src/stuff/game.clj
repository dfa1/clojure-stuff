; Author: Michelangelo Mori
(use 'clojure.contrib.seq-utils)

;; Tiles map definition
(def *tiles*
     {:fox "RED"
      :hare "YELLOW"
      :grass "GREEN"
      :pelt "BLUE"
      :blank "WHITE"})

(def *tiles-limit* 30)

;; Players' functions and varaibles definitions
(def *players*
     {0 "Player One"
      1 "Player Two"})

(def *points*
     {(eval (*players* 0)) 0
      (eval (*players* 1)) 0})

(def *actual-player-key* 0)

(defn- actual-player []
  (*players* *actual-player-key*))

(defn- shift-player []
  (def *actual-player-key*
       (let [c (count (keys *players*))]
	 (mod (+ *actual-player-key* 1) c))))

(defn- add-point []
  (def *points*
       (assoc-in *points* [(actual-player)] (inc (*points* (actual-player))))))

; Coordinates data structure
(defstruct coordinates :x :y)

(defn- random-coordinates []
  (struct coordinates
	  (+ 1 (rand-int 7))
	  (+ 1 (rand-int 7))))

(defn- random-coordinates-list []
  (loop [temp (list)]
    (if (not (= 20 (count (distinct temp))))
      (recur (take 20 (repeatedly random-coordinates)))
      temp)))

(defn- calculate-index [coords] :pre (assert (and (> (:x coords) 0) (> (:y coords) 0))) 
  (let [x (:x coords)
        y (:y coords)]
    (if (and
	 (>= y 1) (<= x 8)
	 (>= y 1) (<= y 8))
      (+ (* 8 (- x 1)) y))))

(def *board*
     (hash-map))

;; Important board definitions
(def empty-board
     (zipmap
      (range 1 65)
      (replicate 64 (*tiles* :blank))))

(def inited-board
     (merge
      empty-board
      (zipmap
       (mapcat (fn [x] (list (calculate-index x))) (random-coordinates-list))
       (concat (repeat 16 (*tiles* :hare)) (repeat 4 (*tiles* :fox))))))

(defn- count-element [value]
  (count (filter (fn [x] (= (val x) value)) *board*)))

(defn- dump-game-state []
  (do
    
    (println "fox " (count-element (*tiles* :fox))))
    (println "hare" (count-element (*tiles* :hare)))
    (println "grass" (count-element (*tiles* :grass)))

)

;; Side effecting functions to modify the board
(defn- get-element-at [coords]
  (let [i (calculate-index coords)]
    (*board* i)))

(defn- set-element-at [coords value]
  (do
    (def *board*
	 (let [i (calculate-index coords)]
	   (do
	     (assoc-in *board* [i] value))))
    (dump-game-state)))

(defn- get-neighbours-of [coords]
  (let [n-coords (struct coordinates (coords :x) (- (coords :y) 1))
        e-coords (struct coordinates (+ (coords :x) 1) (coords :y))
        s-coords (struct coordinates (coords :x) (+ (coords :y) 1))
        w-coords (struct coordinates (- (coords :x) 1) (coords :y))
        n (get-element-at n-coords)
        e (get-element-at e-coords)
        s (get-element-at s-coords)
        w (get-element-at w-coords)]
    (mapcat
     (fn [a b]
       (list (cons a b)))
     (list n e s w)
     (list n-coords e-coords s-coords w-coords))))

(defn- end-game? []
  (or
   (= (count-element (*tiles* :fox)) 0)
   (= (count-element (*tiles* :hare)) 0)))

;; Multiple turns variable and functions
(def *multiple-turns* 0)
(defn- multi-turn []
  (def *multiple-turns* (+ 2 *multiple-turns*)))
(defn- killing-spree-stopped []
  (def *multiple-turns* 0))

(defn- is-blank? [actor]
  (= (*tiles* :blank) actor))
(defn- is-fox? [actor]
  (= (*tiles* :fox) actor))
(defn- is-grass? [actor]
  (= (*tiles* :green) actor))
(defn- is-hare? [actor]
  (= (*tiles* :hare) actor))

(defn- neighbour-filter [func coords]
  (filter
   (fn [x] (func (first x)))
   (get-neighbours-of coords)))

(defn- neighbouring-hares [coords]
  (neighbour-filter is-hare? coords))
(defn- neighbouring-foxes [coords]
  (neighbour-filter is-fox? coords))
(defn- neighbouring-grass [coords]
  (neighbour-filter is-grass? coords))
(defn- neighbouring-blanks [coords]
  (neighbour-filter is-blank? coords))

;; Pull function picks a position and applies the normal rules to it,
;; Shoot function picks a position and applies only the "fox" rule to
;; it.

(defn- go-on [coords]
  (let [selected-actor (get-element-at coords)
	grass-neighbours (neighbouring-grass coords)
	hare-neighbours (neighbouring-hares coords)
	fox-neighbours (neighbouring-foxes coords)]
    (cond
     (is-hare? selected-actor)
     (cond
      (not (empty? fox-neighbours)) (when (< (count-element (*tiles* :fox)) *tiles-limit*)
				      (set-element-at coords (*tiles* :fox))
				      (multi-turn)
				      (go-on coords))
      (not (empty? grass-neighbours)) (let [neighbour (first (shuffle grass-neighbours))
					    neighbour-coords (struct coordinates
								     (nth (nth neighbour 1) 1)
								     (nth (nth neighbour 2) 1))]
					(when (< (count-element (*tiles* :hare)) *tiles-limit*)
					  (set-element-at neighbour-coords (*tiles* :hare))
					  (go-on neighbour-coords))))
     (is-fox? selected-actor)
     (cond
      (not (empty? hare-neighbours)) (let [neighbour (first (shuffle hare-neighbours))
					   neighbour-coords (struct coordinates
								    (nth (nth neighbour 1) 1)
								    (nth (nth neighbour 2) 1))]
				       (when (< (count-element (*tiles* :fox)) *tiles-limit*)
					 (set-element-at neighbour-coords (*tiles* :fox))
					 (multi-turn)
					 (go-on neighbour-coords)))))))

(defn- pull []
  (let [coords (random-coordinates)
	selected-actor (get-element-at coords)
	grass-neighbours (neighbouring-grass coords)
        hare-neighbours (neighbouring-hares coords)
        fox-neighbours (neighbouring-foxes coords)]
    (cond
     (is-blank? selected-actor) (when (< (count-element (*tiles* :grass)) *tiles-limit*)
				  (set-element-at coords (*tiles* :grass))
				  (go-on coords))
     (is-fox? selected-actor)
     (do
       (add-point)
       (set-element-at coords (*tiles* :blank)))
     (is-grass? selected-actor)
     (cond
      (not (empty? hare-neighbours)) (when (< (count-element (*tiles* :hare)) *tiles-limit*)
				       (set-element-at coords (*tiles* :hare))
				       (go-on coords)))
     (is-hare? selected-actor)
     (cond
      (not (empty? fox-neighbours)) (when (< (count-element (*tiles* :fox)) *tiles-limit*)
				      (set-element-at coords (*tiles* :fox))
				      (multi-turn)
				      (go-on coords))
      (not (empty? grass-neighbours)) (let [neighbour (first (shuffle grass-neighbours))
					    neighbour-coords (struct coordinates
								     (nth (nth neighbour 1) 1)
								     (nth (nth neighbour 2) 1))]
					(when (< (count-element (*tiles* :hare)) *tiles-limit*)
					  (set-element-at neighbour-coords (*tiles* :hare))
					  (go-on neighbour-coords)))))))

(defn- shoot []
  (dotimes [_ *multiple-turns*]
    (let [coords (random-coordinates)
	  selected-actor (get-element-at coords)]
      (cond
       (is-fox? selected-actor) (do
				  (add-point)
				  (set-element-at coords (*tiles* :blank)))))))

;; Main game loop
(defn game-loop []
  (do
    (def *board* (merge empty-board inited-board))
    (dump-game-state)
    (while (not (end-game?))
	   (pull)
	   (if (> *multiple-turns* 0)
	     (do
	       (shoot)
	       (killing-spree-stopped)))
	   (shift-player))))


