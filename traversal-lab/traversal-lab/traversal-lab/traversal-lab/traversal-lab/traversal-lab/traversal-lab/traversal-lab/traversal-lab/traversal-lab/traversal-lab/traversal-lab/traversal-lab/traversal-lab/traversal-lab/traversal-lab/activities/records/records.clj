(defrecord Triple [x y z])

t1->[10,20,30]
    ^         t2->[40,50,60]
    |             ^         t3->[70,80,90]
t4__|_____________|_____________^

(def t4 (Triple. 40 99 80))
(def t3 (Triple. 30 t4 70))
(def t2 (Triple. 20 t3 60))
(def t1 (Triple. 10 t2 50))

(:z t4)
