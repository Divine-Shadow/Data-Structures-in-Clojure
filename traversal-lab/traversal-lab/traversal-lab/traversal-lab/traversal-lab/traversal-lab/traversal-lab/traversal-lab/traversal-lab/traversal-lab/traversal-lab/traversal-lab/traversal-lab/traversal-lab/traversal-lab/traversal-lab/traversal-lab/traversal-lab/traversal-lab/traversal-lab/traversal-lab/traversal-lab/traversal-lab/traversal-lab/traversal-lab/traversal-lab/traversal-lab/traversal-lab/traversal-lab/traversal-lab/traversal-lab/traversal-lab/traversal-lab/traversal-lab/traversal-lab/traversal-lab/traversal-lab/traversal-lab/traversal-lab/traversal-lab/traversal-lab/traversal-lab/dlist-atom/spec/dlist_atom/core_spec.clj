(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


;(describe "The spec file"
 ;         (it "should have some tests."
  ;            (should false)))
(describe "insert-front"
          (it "should work for an empty dlist"
              (let [xx (dlist)]
                (insert-front xx 2)
                (should= '(2) (show-dlist xx))
                (should= 1 @(:size xx))))
          (it "should work for dlists with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 5)
                (should= '(5 2) (show-dlist xx))
                (should= 2 @(:size xx)))))
(describe "insert-last"
          (it "should work for an empty dlist"
              (let [xx (dlist)]
                (insert-last xx 2)
                (should= '(2) (show-dlist xx))
                (should= 1 @(:size xx))))
          (it "should work multiple times"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-last xx 5)
                (insert-last xx 8)
                (should= '(4 2 5 8) (show-dlist xx))
                (should= 4 @(:size xx)))))
(describe "insert-sorted"
         (it "should work for an empty dlist"
              (let [xx (dlist)]
                (insert-sorted xx 2)
                (should= '(2) (show-dlist xx))
                (should= 1 @(:size xx))))
          (it "should work for a dlist where element needs to be placed in the front"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 1)
                (insert-sorted xx 0)
                (should= '(0 1 2) (show-dlist xx))
                (should= 3 @(:size xx))))
          (it "should work for a dlist with data multiple times"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-last xx 4)
                (insert-sorted xx 3)
                (insert-sorted xx 3.5)
                (should= '(2 3 3.5 4) (show-dlist xx))
                (should= 4 @(:size xx)))))
(describe "index-forward"
          (it "should work for a dlist where the element is not in dlist"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 4)
                (should= true (nil? (index-forward xx 5)))))
          (it "should work for a dlist with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 5)
                (insert-front xx 4)
                (should= 1 (index-forward xx 5)))))

(describe "index-backward"
          (it "should work for a dlist with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 5)
                (insert-front xx 4)
                (should= -2 (index-backward xx 5)))))
(describe "list-to-dlist"
          (it "should work for clojure lists"
              (let [xx (cons 2 (cons 4 nil))]
                (should= '(2 4) (show-dlist (list-to-dlist xx))))))
(describe "delete"
          (it "should work for dlists where the victim is not in the dlist"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-front xx 5)
                (delete xx 3)
                (should= '(5 4 2) (show-dlist xx))))
          (it "should work for dlists where the victim is in the list"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-front xx 5)
                (delete xx 2)
                (should= '(5 4) (show-dlist xx))))
          (it "should work for lists which are not sorted"
              (let [xx (dlist)]
                (insert-front xx 4)
                (insert-front xx 2)
                (insert-front xx 8)
                (insert-front xx 5)
                (insert-last xx 25)
                (delete xx 2)
                (should= '(5 8 4 25) (show-dlist xx))
                (delete xx 8)
                (should= '(5 4 25) (show-dlist xx))))) 
(describe "reverse"
          (it "should work for an empty dlist"
              (let [xx (dlist)]
                (reverse xx)
                (should= '() (show-dlist xx))))
          (it "should work for a dlist with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 1)
                (insert-front xx 0)
                (reverse xx)
                (should= '(2 1 0) (show-dlist xx))))
          (it "should work for the following similar test"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 5)
                ;(insert-front xx 6)
                ;(insert-front xx 2)
                ;(insert-front xx 5)
                (reverse xx)
                (should= '(2 5) (show-dlist xx)))))
(describe "show-dlist-reverse" 
          (it "should work for an empty dlist"
              (let [xx (dlist)]
                (should= '() (show-dlist-reverse xx))))
          (it "should work for a dlist with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-front xx 5)
                (insert-front xx 8)
                (should= '(2 4 5 8) (show-dlist-reverse xx)))))
(run-specs)


