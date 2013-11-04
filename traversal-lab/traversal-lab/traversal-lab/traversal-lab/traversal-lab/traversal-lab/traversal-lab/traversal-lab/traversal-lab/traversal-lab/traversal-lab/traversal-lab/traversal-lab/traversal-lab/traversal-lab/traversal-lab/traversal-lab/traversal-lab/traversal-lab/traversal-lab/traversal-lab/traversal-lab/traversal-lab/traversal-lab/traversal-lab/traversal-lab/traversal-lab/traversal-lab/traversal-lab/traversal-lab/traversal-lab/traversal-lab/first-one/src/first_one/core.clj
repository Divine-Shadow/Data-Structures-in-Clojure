(ns first_one.core)

;; Here is my foo function.

(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;; Take the absolute value. I think it's buggy.

(defn abs [x]
  (if (> x 0) (- x) x))
  
;; This is the bar function.  

(defn bar [x y ]
  "A function to add x to y."
   (+ (abs x) (abs y)))
