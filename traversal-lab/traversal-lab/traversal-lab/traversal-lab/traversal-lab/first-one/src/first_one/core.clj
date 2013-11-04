(ns first_one.core)

(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;; Take the absolute value

(defn abs [x]
  (if (> x 0) (- x) x))
;; here is the bar
(defn bar [x y ]
  "A function to add x to y."
   (+ (abs x) (abs y)))
;; Here is my comment 

