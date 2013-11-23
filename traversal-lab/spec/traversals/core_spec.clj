(ns traversals.core-spec
  (:require [speclj.core :refer :all]
            [traversals.core :refer :all])
  (:import [traversals.core BNode])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(def basecase (reduce add nil '(8 4 3 7 9  11)))
(describe "The spec file"
          (it "should have some tests."
              (should true)))


(describe "preorder swap"
(it "should not swap" (should= (preorder basecase) '( 8 4 3 7 9  11))) 
)


(describe "inorder"
(it "should not swap" (should= (inorder basecase) '(3 4 7 8  9 11))) 
)


(describe "postorder"
(it "should not swap" (should= (postorder basecase) '(3 7 4  11 9 8))) 
)

(describe "frontier"
(it "should not swap" (should= (preorder basecase) '(3 7  11))) 
)


(describe "levelorder"
(it "should not swap" (should= (levelorder basecase) '(8 4 9 2 7  11))) 
)


(run-specs)
