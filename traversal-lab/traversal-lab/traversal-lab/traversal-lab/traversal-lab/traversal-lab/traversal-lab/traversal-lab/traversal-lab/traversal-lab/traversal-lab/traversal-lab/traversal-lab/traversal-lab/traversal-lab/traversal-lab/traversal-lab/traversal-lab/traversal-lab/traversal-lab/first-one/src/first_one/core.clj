(ns first_one.core)

;; This is the "foo" function, which tells us whether or not x is greater than y
(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;; This is the "abs" function, which gives us the absolute value of a number x
(defn abs [x]
  (if (< x 0) 
    (- x) 
    x))

;; This is the "bar" function, which adds two absolute values together, x and y
(defn bar [x y]
  "A function to add x to y."
   (+ (abs x) (abs y)))

