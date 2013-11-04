(ns dlist-atom.core-spec
 ; (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
 ; (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.
;;(describe "DList"
;;          (it "have a sentinel"
  ;;            (should= 1 (:sentinel (DList. 1 1))))
    ;;      (it "should have a size"
      ;;        (should= 1 (:size (DList. 1 1)))))

(describe "insert-front"
          (it "should increase size by 1"
              (let [p (dlist)] (should= (+ 1 (d-size p))
                                        (do (insert-front p 5) (d-size p)))))
          (it "should set the previous link"
              (let [p (dlist)]
                (should= (do (insert-front p 2) (insert-front p 3) (show-dlist p)) '(3 2) )))
          (it "should set the next link"
              (let [p (dlist)] (should= (do (insert-front p 2) (insert-front p 3)
(show-dlist-reverse p)) '(2 3) ))))
                                               
(describe "insert-last"
           (it "should increase size by 1"
              (let [p (dlist)] (should= (+ 1 (d-size p))
                                        (do (insert-front p 5) (d-size p)))))
           )


(describe "insert-sorted"
           (it "should increase size by 1"
              (let [p (dlist)] (should= (+ 1 (d-size p))
                                        (do (insert-front p 5) (d-size p)))))
           (it "should set previous link"
               (let [p (dlist)]
                 (should= (do (insert-last p 2) 
                              (insert-last p 5)
                              (insert-sorted p 3)
                              (show-dlist p)) '(2 3 5))))
           (it "should set previous link2"
               (let [p (dlist)]
                 (should=  (do (insert-last p 2) 
                              (insert-last p 5)
                              (insert-sorted p 3)
                              (show-dlist-reverse p)) '(5 3 2))))
            (it "should set next link"
               (let [p (dlist)]
                 (should=  (do (insert-last p 2) 
                              (insert-last p 3)
                              (insert-sorted p 1)
                              (show-dlist-reverse p)) '(3 2 1))))
)          
(describe "index-forward"
          (it "should find the index" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (index-forward p 3)) 1)))
          (it "should return 0"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (index-forward p 9)) nil))))

(describe "index-backward"
          (it "should find the index"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (index-backward p 3)) -2))))

(describe "list-to-dlist"
          (it "should work going forward"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (show-dlist p)) (show-dlist (list-to-dlist '(2 6 5)))))))
(describe "delete"
          (it "should make a deletion"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (delete p 5)) '(2 3))))
          (it "should make a deletion"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (delete 2)) '(3 5))))
           (it "should decrease size by 1"
              (let [p (dlist)] (should= 2
                                        (do (insert-front p 5) (insert-lastp 3) (insert-last p 5) (delete p 5) (d-size p))))))
(describe "reverse"
          (it "should fix forward edges"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (reverse p) (show-dlist p)) '(5 3 2))))
          (it "should work backwards as well"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (reverse p) (show-dlist-reverse)) '(2 6 5)))))

(describe "show-dlist-reverse"
          (it "should work going forward"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (show-dlist p)) (show-dlist (list-to-dlist '(2 3 5))))))
          (it "should work going backward"  (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (show-d-list-reverse p)) (show-dlist (list-to-dlist '(5 3 2)))))))
(run-specs)
