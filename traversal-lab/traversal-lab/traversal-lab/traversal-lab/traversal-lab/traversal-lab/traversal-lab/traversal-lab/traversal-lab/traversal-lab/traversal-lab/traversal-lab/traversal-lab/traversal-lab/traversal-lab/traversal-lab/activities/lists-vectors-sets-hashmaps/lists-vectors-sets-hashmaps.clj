;; Activity lists-vectors-sets-hashmaps
(def x1 '(1 2 3 4 5))
(def list-product [xs] (apply * xs))
(list-product x1)
(def list-product2 [xx] (if (empty? xx) 1 (* (first xx) (list-product2 (rest xx)))))
(list-product2 x1)

(def v1 [1 0 2 0 3 0 4 0 5])
(defn countv-zero [vx] (count (filterv #(= % 0) vx)))
(countv-zero v1)

(defn list-to-set [xs] (into #{} xs))
(list-to-set x1)

(def empsv [{:name "Joe" :salary 10.00} {:name "Jane" :salary 10.01} {:name "John" :salary 10.02}])
(defn get-salary [empv name] (if (= ((first empv) :name) name) ((first empv) :salary) (get-salary (rest empv) name)))
(get-salary empsv "John")
