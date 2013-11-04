(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(describe "insert-front"
          (it "should work on an empty list - convert to list"
              (let [xx (dlist)]
                (insert-front xx 20)
                (should= '(20) (show-dlist xx))))
          
          (it "should set size to one for an empty list"
              (let [xx (dlist)]
                (insert-front xx 20)
                (should= 1 (d-size xx))))
          
          (it "should increment size"
              (let [xx (dlist)]
                (insert-front xx 20)
                (insert-front xx 10)
                (should= 2 (d-size xx))))

          (it "should convert to list for multiple insert-front"
              (let [xx (dlist)]
                (insert-front xx 30) (insert-front xx 20)
              (should= '(20 30) (show-dlist xx))
              (insert-front xx 10)
              (should= '(10 20 30) (show-dlist xx))))
)

(describe "insert-last"
          (it "should work on an empty list - convert to list"
              (let [xx (dlist)]
                (insert-last xx 20)
                (should= '(20) (show-dlist xx))))

          (it "should set size to one for an empty list"
              (let [xx (dlist)]
                (insert-last xx 20)
                (should= 1 (d-size xx))))

          (it "should increment size"
              (let [xx (dlist)]
                (insert-last xx 20)
                (insert-last xx 10)
                (should= 2 (d-size xx))))

          (it "should convert to list for multiple insert-last"
              (let [xx (dlist)]
                (insert-last xx 20) (insert-last xx 30)
              (should= '(20 30) (show-dlist xx))
              (insert-last xx 10)
              (should= '(20 30 10) (show-dlist xx))))
)

(describe "insert-sorted"
          (it "should work on an empty list - convert to list"
              (let [xx (dlist)]
                (insert-sorted xx 20)
                (should= '(20) (show-dlist xx))))

          (it "should set size to one for an empty list"
              (let [xx (dlist)]
                (insert-sorted xx 20)
                (should= 1 (d-size xx))))

          (it "should increment size"
              (let [xx (dlist)]
                (insert-sorted xx 20)
                (insert-sorted xx 10)
                (should= 2 (d-size xx))))

          (it "should convert to list for multiple insert-sorted"
              (let [xx (dlist)]
                (insert-sorted xx 20) (insert-sorted xx 30)
                (should= '(20 30) (show-dlist xx))
                (insert-sorted xx 25)
                (should= '(20 25 30) (show-dlist xx))))
)

(describe "index-forward"
          (it "should return nil if elt is not in the list"
              (let [xx (dlist)]
                (insert-front xx 10) (insert-front xx 5)
                (should= nil (index-forward xx 0))
                (should= nil (index-forward xx 7))))

          (it "should return nil on empty lists"
              (let [xx (dlist)]
                (should= nil (index-forward xx 0))
                (should= nil (index-forward xx 320))))

          (it "should work for full lists"
              (let [xx (dlist)]
                (insert-front xx 40) (insert-front xx 30) (insert-front xx 25)
                (should= 1 (index-forward xx 30))
                (should= 0 (index-forward xx 25))
                (should= 2 (index-forward xx 40))
                (should= nil (index-forward xx 20)))))

(describe "index-backward"
          (it "should return nil if elt is not in the list"
              (let [xx (dlist)]
                (insert-front xx 10) (insert-front xx 5)
                (should= nil (index-backward xx 0))
                (should= nil (index-backward xx 7))))

          (it "should return nil on empty lists"
              (let [xx (dlist)]
                (should= nil (index-backward xx 0))
                (should= nil (index-backward xx 320))))

          (it "should work for full lists"
              (let [xx (dlist)]
                (insert-front xx 40) (insert-front xx 30) (insert-front xx 25)
                (should= -2 (index-backward xx 30))
                (should= -3 (index-backward xx 25))
                (should= -1 (index-backward xx 40))
                (should= nil (index-backward xx 20)))))

(describe "list-to-dlist"
          (it "should return an empty dlist for an empty list"
              (should= (show-dlist (dlist)) (show-dlist (list-to-dlist '())))
              (should= (show-dlist-reverse (dlist)) (show-dlist-reverse (list-to-dlist '()))))
          
          (it "should create a dlist with the correct size"
              (should= 3 (d-size (list-to-dlist '(1 3 2))))
              (should= 6 (d-size (list-to-dlist '(1 3 2 5 6 7)))))

          (it "should return a valid dlist"
              (let [xx (dlist)]
                (insert-sorted xx 20) (insert-sorted xx 30) (insert-sorted xx 23)
                (should= (show-dlist xx) (show-dlist (list-to-dlist '(20 23 30))))
                (should= (show-dlist-reverse xx) (show-dlist-reverse (list-to-dlist '(20 23 30)))))))

(describe "reverse"
          (it "return an empty list for an empty list"
              (let [xx (dlist)]
                (reverse xx)
                (should= (show-dlist xx) (show-dlist (dlist)))
                (should= (show-dlist-reverse xx) (show-dlist-reverse (dlist)))))
          
          (it "should not change the size of the list"
              (let [xx (dlist)]
                (insert-sorted xx 30) (insert-sorted xx 20) (insert-sorted xx 27)
                (should= 3 (do (reverse xx) (d-size xx)))
                (insert-sorted xx 200)
                (should= 4 (do (reverse xx) (d-size xx)))))

          (it "should return a reverse dlist which can be traversed forward and backward"
              (let [xx (dlist)]
                (insert-sorted xx 30) (insert-sorted xx 20) (insert-sorted xx 27)
                (should= '(20 27 30) (show-dlist xx))
                (reverse xx)
                (should= '(30 27 20) (show-dlist xx))
                (should= '(20 27 30) (show-dlist-reverse xx)))))

(describe "delete"
          (it "should return an empty list for an empty list"
              (let [xx (dlist)]
                (delete xx 20)
                (should= '() (show-dlist xx))))
          
          (it "should keep size at zero for an empty list"
              (let [xx (dlist)]
                (delete xx 20)
                (should= 0 (d-size xx))))
          
          (it "should decrement size only if element exists in list"
              (let [xx (dlist)]
                (insert-sorted xx 15) (insert-sorted xx 20) (insert-sorted xx 30)
                (delete xx 20)
                (should= 2 (d-size xx))
                (delete xx 10)
                (should= 2 (d-size xx))
                (delete xx 15)
                (should= 1 (d-size xx))
                (delete xx 30)
                (should= 0 (d-size xx))))

          (it "should convert to list for multiple delete"
              (let [xx (dlist)]
                (insert-sorted xx 20) (insert-sorted xx 15) (insert-sorted xx 30)
                (delete xx 30)
                (should= '(15 20) (show-dlist xx))
                (should= '(20 15) (show-dlist-reverse xx))
                (delete xx 15)
                (should= '(20) (show-dlist xx))
                (should= '(20) (show-dlist-reverse xx))
                (delete xx 20)
                (should= '() (show-dlist xx))
                (should= '() (show-dlist-reverse xx)))))
                

(describe "show-dlist-reverse"
 
          (it "should returned the a normal reversed list for a dlist"
              (let [xx (dlist)]
                (insert-front xx 20) (insert-front xx 30)
                (should= '(30 20) (show-dlist xx))
                (should= '(20 30) (show-dlist-reverse xx))
                (insert-last xx 5)
                (should= '(30 20 5) (show-dlist xx))
                (should= '(5 20 30) (show-dlist-reverse xx))))

          (it "should end with 'infinite-loop if a misdirect :prev creates an infinite loop"
              (let [xx (dlist)]
                (insert-front xx 20) (insert-front xx 30)
                (reset-d-prev! (-> xx d-sentinel d-prev d-prev) (-> xx d-sentinel d-prev)) ;insert infinite loop
                (should= 'infinite-loop (-> xx show-dlist-reverse clojure.core/reverse first)))

              (let [xx (dlist)]
                (insert-front xx 20) (insert-front xx 30) (insert-front xx 40)
                (reset-d-prev! (-> xx d-sentinel d-prev d-prev d-prev) (-> xx d-sentinel d-prev)) ;insert infinite loop
                (should= 'infinite-loop (-> xx show-dlist-reverse clojure.core/reverse first))))

              
)

(run-specs)
