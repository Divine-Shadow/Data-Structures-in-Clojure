(ns first_one.core)

;; Foo function
(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;; Take abs val - buggy
(defn abs [x]
  (if (< x 0) (- x) x))

(defn bar [x y ]
  "A function to add x to y."
   (+ (abs x) (abs y)))
