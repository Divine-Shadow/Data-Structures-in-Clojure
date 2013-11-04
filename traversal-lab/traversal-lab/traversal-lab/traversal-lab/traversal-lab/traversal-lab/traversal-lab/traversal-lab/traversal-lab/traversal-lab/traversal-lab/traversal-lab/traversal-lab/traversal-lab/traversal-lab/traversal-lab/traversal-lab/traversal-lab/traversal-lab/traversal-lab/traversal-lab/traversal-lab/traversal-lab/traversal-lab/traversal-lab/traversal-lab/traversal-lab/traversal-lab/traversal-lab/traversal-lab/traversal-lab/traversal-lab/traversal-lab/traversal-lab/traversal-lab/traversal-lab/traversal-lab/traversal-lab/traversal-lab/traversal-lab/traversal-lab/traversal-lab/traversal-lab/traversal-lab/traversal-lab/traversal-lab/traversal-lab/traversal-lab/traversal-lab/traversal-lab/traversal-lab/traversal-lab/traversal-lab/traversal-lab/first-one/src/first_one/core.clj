(ns first_one.core)

;;comment function
(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;;take the asbolute value. another comment
(defn abs [x]
  (if (< x 0) (- x) x)) ;;changed (> x 0) to (< x 0)


;;this is bar
(defn bar [x y]
 "A function to add x to y."
   (+ (abs x) (abs y)))
