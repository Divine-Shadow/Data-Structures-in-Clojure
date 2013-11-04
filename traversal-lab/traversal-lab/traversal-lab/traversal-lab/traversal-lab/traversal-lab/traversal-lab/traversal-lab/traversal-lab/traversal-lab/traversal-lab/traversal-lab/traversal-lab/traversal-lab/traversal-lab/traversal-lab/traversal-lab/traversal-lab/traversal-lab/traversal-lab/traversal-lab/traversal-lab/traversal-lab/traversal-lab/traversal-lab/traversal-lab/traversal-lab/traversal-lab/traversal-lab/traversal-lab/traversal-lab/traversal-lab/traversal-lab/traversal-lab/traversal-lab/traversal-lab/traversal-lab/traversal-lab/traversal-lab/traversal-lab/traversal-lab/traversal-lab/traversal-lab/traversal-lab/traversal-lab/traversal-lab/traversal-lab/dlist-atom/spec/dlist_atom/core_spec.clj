(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The spec file"
          (it "should have some tests."
              (should true)))
(describe "Insert front"
          (it "should incrememnt size."
              (should= 1 (let [xx (dlist)]  
                           (insert-front xx 2)
                           (d-size xx))))
          (it "should set previous link"
              (should= 6 (let [xx (list-to-dlist '(3 4 5 6))]  
                             (insert-front xx 6)
                             (-> xx d-sentinel d-next d-next d-prev d-data)))))
(describe "insert last"
          (it "should insert properly"
              (should= '(1 2 3 4 5 6) 
                       (let [xx (list-to-dlist '(1 2 3 4 5))] (insert-last xx 6) (show-dlist xx)))))
(describe "insert-sorted"
          (it "should insert properly"
              (should= '(6 5 4 3 2 1) 
                       (let [xx (list-to-dlist '(1 2 4 5 6))] (insert-sorted xx 3) (show-dlist-reverse xx)))))
(describe "index-forward"
          (it "should return nil"
              (should= nil (index-forward 
                             (list-to-dlist '(1 2 3)) 4))))
(describe "index backward"
          (it "should work properly"
              (should= -2 (index-backward 
                            (list-to-dlist '(1 2 3 4)) 3))))
(describe "delete"
          (it "should not assume the list is sorted"
              (should= '(1 5 3 2 1) (let [xx (list-to-dlist '(1 5 4 3 2 1))] 
                                      (delete xx 4) 
                                      (show-dlist xx)))))
(describe "list to DList" 
          (it "should work forwards"
              (should= '(1 2 3 4) (show-dlist 
                                    (list-to-dlist '(1 2 3 4)))))
          (it "should work backwards"
              (should= '(4 3 2 1) (show-dlist-reverse
                                    (list-to-dlist '(1 2 3 4))))))
(describe "reverse"
          (it "should work properly"
              (should= '(4 3 2 1) (let [xx (list-to-dlist '(1 2 3 4))] 
                                    (reverse xx)
                                    (show-dlist xx)))
              (should= '(1 2 3 4) (let [xx (list-to-dlist '(1 2 3 4))] 
                                    (reverse xx)
                                    (show-dlist-reverse xx)))))


(run-specs)
