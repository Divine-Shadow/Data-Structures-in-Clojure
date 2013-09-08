(ns first_one.core)
;; her is my foo function
(defn foo [x y]
  "A function to test if x > y."
  (> x y))
;; take the absolute avlue, i think its buggy
(defn abs [x]
  (if (< x 0) (- x) x))

(defn bar [x y ]
  "A function to add x to y."
   (+ (abs x) (abs y)))
