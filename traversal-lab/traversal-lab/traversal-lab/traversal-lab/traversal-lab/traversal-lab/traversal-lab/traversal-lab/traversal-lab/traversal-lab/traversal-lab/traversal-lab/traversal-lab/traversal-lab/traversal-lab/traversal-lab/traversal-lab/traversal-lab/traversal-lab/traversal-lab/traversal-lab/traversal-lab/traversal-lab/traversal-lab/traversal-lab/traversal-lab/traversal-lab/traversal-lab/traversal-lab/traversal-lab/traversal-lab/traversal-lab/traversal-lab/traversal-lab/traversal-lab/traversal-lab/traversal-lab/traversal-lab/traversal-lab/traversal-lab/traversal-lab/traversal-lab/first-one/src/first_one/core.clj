(ns first_one.core)
;;first function
(defn foo [x y]
  ;;"A function to test if x > y."
  (> x y))

(defn abs [x]
  (if (>= x 0) x (-x)))

(defn bar [x y]
  ;;"A function to add x to y."
   (+ x y))