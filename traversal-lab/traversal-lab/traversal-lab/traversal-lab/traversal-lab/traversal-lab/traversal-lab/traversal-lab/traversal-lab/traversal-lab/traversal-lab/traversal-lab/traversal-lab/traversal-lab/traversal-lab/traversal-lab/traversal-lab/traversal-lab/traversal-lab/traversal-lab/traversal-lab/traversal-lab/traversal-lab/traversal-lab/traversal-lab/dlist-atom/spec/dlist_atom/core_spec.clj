(ns dlist-atom.core-spec
  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The spec file"
          (it "should have some tests."
              (should true)))

(describe "insert-front"
          (it "should work with empty DLists."
              (let [xx (dlist)]
                (insert-front xx 0)
                (should= '(0) (show-dlist xx))))

          (it "should work with DLists with data in them already."
              (let [xx (dlist)]
                (insert-front xx 5)
                (insert-front xx 6)
                (should= '(6 5) (show-dlist xx))))

          (it "should increase the size of the DList."
              (let [xx (dlist)]
                (insert-front xx 5)
                (should= 1 (d-size xx))))
)

(describe "insert-last"

          (it "should work with empty DLists."
              (let [xx (dlist)]
                (insert-last xx 0)
                (should= '(0) (show-dlist xx))))

          (it "should work with DLists with data in them."
              (let [xx (dlist)]
                (insert-last xx 10)
                (insert-last xx 15)
                (should= '(10 15) (show-dlist xx))))

          (it "should work with insert-first."
              (let [xx (dlist)]
                (insert-front xx 10)
                (insert-front xx 15)
                (insert-last xx 20)
                (should= '(15 10 20) (show-dlist xx))))

          (it "should increase the size when the elt is added to an empty DList."
              (let [xx (dlist)]
                (insert-last xx 10)
                (should= 1 (d-size xx))))

          (it "should increase the size when the elt is added to a DList with data."
              (let [xx (dlist)]
                (insert-last xx 10)
                (insert-last xx 15)
                (should= 2 (d-size xx))))
)

(describe "insert-sorted"

          (it "should work with empty DLists."
              (let [xx (dlist)]
                (insert-sorted xx 10)
                (should= '(10) (show-dlist xx))))

          (it "should work with DLists with data."
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-last xx 2)
                (insert-sorted xx 3)
                (should= '(1 2 3) (show-dlist xx))))

          (it "should increase the size of DList when the elt is added to an empty DList."
              (let [xx (dlist)]
                (insert-sorted xx 1)
                (should= 1 (d-size xx))))

          (it "should increase the size when the elt is added to a DList with data."
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 1)
                (insert-last xx 3)
                (insert-sorted xx 4)
                (should= 4 (d-size xx))))

          (it "should work with a DList with data."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (insert-last xx 5)
                (insert-sorted xx 4)
                (should= '(1 2 3 4 5) (show-dlist xx))))

)

(describe "index-forward"

          (it "should work when the DList has one element."
              (let [xx (dlist)]
                (insert-front xx 1)
                (should= 0 (index-forward xx 1))))

          (it "should work when the elt is somewhere in the list."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (should= 1 (index-forward xx 2))))

          (it "should work when the element isn't in the DList."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (should= nil (index-forward xx 5))))

          (it "should work when the element is in the middle of the DList."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (insert-last xx 4)
                (should= 2 (index-forward xx 3))))
)

(describe "index-backward"

          (it "should work with empty DLists."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (should= nil (index-backward xx 5))))

          (it "should work when the elt is the first in the DList."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (should= -3 (index-backward xx 1))))

          (it "should work when the elt is in the middle of the DList."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (insert-last xx 4)
                (should= -2 (index-backward xx 3))))

          (it "should work when the elt is the last elt in the DList."
              (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (should= -1 (index-backward xx 3))))

)

(describe "list-to-dlist"

          (it "should work when the list is empty."
              (let [lst '()
                    xx (dlist)]
                (should= xx (list-to-dlist lst))))

          (it "should work when the list has data."
              (let [lst '(1 2 3)
                    xx (dlist)]
                (insert-front xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (should= lst (show-dlist (list-to-dlist lst)))))

)


(run-specs)
