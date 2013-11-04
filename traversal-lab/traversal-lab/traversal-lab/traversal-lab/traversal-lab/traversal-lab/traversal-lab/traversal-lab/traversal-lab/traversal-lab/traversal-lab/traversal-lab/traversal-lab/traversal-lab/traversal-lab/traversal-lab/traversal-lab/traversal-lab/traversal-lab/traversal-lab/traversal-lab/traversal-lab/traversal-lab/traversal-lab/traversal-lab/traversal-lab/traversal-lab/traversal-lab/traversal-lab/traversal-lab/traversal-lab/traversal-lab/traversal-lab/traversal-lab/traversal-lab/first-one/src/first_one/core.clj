(ns first_one.core)

;; Here is my foo function

(defn foo [x y]
  "A function to test if x > y."
  (> x y))

;; Take the absolute value, it probably works fine EDIT: Maybe not...

(defn abs [x]
  (if (< x 0) (- x) x))

(defn bar [x y ]
  "A function to add x to y."
   (+ (abs x) (abs y)))

;;Personal Test
(def vec [0,1,2,3,4,0,5,0])
(defn zero-count [x] (count (filterv #(= % 0) x)))

(def list1 '(1,2,3,4))
(defn list-to-set [l1] (into #{} l1))

(def employee1 {:name "Jacob" :salary 12000})
(def employee2 {:name "James" :salary 90000})
(def employees [employee1 employee2])
(defn emp-salary [emp name] (if (= (emp :name) name) (emp :salary)))
(defn return-salary [emps name] (first (filterv #(not= % nil) (mapv #(emp-salary % name) emps))))
