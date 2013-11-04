(ns test1.main)
(def vec [0,1,2,3,4,0,5,0])
(defn zero-count [x] (if(empty? x) (+ 0) (if(= first 0) (+ 1) (zero-count (rest x)))))
