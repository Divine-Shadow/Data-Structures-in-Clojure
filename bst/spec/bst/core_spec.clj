(ns bst.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [bst.core :refer :all])
;  (:import [bst.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The spec file"
          (it "should have some tests."
              (should true)))


(describe "add"

          (it "should not increment size" (should= 2 (size (add (add (make-tree) 2 4) 4 "apple"))))
          (it "should not increment size" (should= 1 (size (add (make-tree) 4 "apple"))))
          (it "should not swap left and right" (should= (:left (:root (add (add (make-tree) 4 "apple") 3 "bannana")))  (:left (:root (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear")))))
)

(describe "find-key" 
          (it "should check both sides for find" (should (find (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 9)))
          (it "should check both sides for key" (should (find-key (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "pear")))
 (it "should check both sides for find" (should (find   (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 3)))
          (it "should check both sides for key" (should (find-key (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "bannana")))
)

(describe "delete"

          (it "should decrement size" (should= 2 (size (delete (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 3) ) ))
 (it "should decrement size" (should= 1 (size (delete (delete (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 3) 9) ))
          (it "should delete value" (should=  (delete (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 4) (add (add (make-tree)  3 "bannana") 9 "pear")            ))
          (it "should delete value" (should=  (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 9) (add (add (make-tree) 4 "apple")  3 "bannana")))
          (it "should delete value" (should=  (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") 3) (add (add (make-tree) 4 "apple")  9 "pear")))
        
))


(describe "delete-value"

          (it "should decrement size" (should= 2 (size (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "apple") ) ))
          (it "should delete value" (should=  (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "apple") (add (add (make-tree)  3 "bannana") 9 "pear")            ))
          (it "should delete value" (should=  (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "pear") (add (add (make-tree) 4 "apple")  3 "bannana")))
          (it "should delete value" (should=  (delete-value (add (add (add (make-tree) 4 "apple") 3 "bannana") 9 "pear") "bannana") (add (add (make-tree) 4 "apple")  9 "pear")))
)


(describe "map-tree" 

(it "should map values" (should= (map-tree (add (add (add (make-tree) 4 1) 3 2) 9 3) inc) (add (add (add (make-tree) 4 2) 3 3) 9 4)))


)



(run-specs)
