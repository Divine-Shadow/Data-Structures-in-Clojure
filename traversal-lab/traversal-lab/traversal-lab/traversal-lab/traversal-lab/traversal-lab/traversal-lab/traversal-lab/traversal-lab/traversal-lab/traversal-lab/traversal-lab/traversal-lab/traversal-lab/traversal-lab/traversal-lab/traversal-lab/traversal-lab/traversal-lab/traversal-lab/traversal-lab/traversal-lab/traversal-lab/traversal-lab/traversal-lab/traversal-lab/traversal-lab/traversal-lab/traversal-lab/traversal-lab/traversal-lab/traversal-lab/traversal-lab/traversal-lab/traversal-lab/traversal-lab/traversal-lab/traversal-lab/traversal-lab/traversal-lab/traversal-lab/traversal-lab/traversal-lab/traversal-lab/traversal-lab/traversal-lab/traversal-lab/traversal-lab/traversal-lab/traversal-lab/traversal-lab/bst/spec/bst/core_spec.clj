(ns bst.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [bst.core :refer :all])
;  (:import [bst.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "add"
          
          (it "should make a root node for an empty list"
              (should= (make-tree (make-node 3 "Ben") 1) (add (make-tree) 3 "Ben")))

          (it "should only increment the size when a node is added"
              (should= 2 (size (add (make-tree (make-node 3 "Ben") 1) 4 "Tiff")))
              (should= 2 (size (add (make-tree (make-node (make-node 3 "Ben") 4 "Tiff" nil) 2) 3 "Rikin"))))
)

(run-specs)
