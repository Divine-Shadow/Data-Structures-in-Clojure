
(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The record declaration"
          (it "should create something"
              (should (Cons. 10 20)))

          (it "should have a car"
              (should= 10 (:car (Cons. 10 20))))

          (it "should have a cdr"
              (should= 20 (:cdr (Cons. 10 20))))

          (it "should be chainable"
              (should= 40 (-> (Cons. 10 (Cons. 20 (Cons. 30 40))) :cdr :cdr :cdr))))

(describe "insert-at-beginning"
          (it "creates a cons cell"
              (should= (Cons. 10 nil)  (insert-at-beginning 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil) ))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx) ))))

(describe "insert-at-end"
          (it "creates a cons cell"
	  	(should= (Cons. 40 nil) (insert-at-end 40 nil)))

	   (it "should work with empty lists"
	  	(should= (Cons. 40 nil) (insert-at-end 40 nil)))

	  (it "should work with lists that have data"
	       (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	         (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))) (insert-at-end 40 xx)))))

(describe "sorted insert"
           (it "creates a cons cell"
	  	(should= (Cons. 20 nil) (sorted-insert 20 nil)))

	   (it "should work with empty lists"
	  	(should= (Cons. 20 nil) (sorted-insert 20 nil)))

  	   (it "should work with lists that have data"
	   	(let [xx (Cons. 10 (Cons. 30 nil))]
		   (should= (Cons. (:car xx) (Cons. 20 (:cdr xx))) (sorted-insert 20 xx))))
	  
	   (it "should recycle memory"
	   	(let [xx (Cons. 10 (Cons. 30 nil))]
		    (should= true (identical? (:cdr xx) (:cdr (:cdr (sorted-insert 20 xx))))))))	



(describe "search"
           (it "should work with empty lists"
	  	(should= false (search 20 nil)))

	   (it "should declare if an element is not in a list"
		(let [xx (Cons. 10 (Cons. 30 nil))]
		(should= false (search 20 xx))))

  	   (it "should declare if an element is in a list"
	   	(let [xx (Cons. 10 (Cons. 30 nil))]
		   (should= true (search 30 xx)))))




(describe "delete"
           (it "should work with empty lists"
	  	(should= nil (delete 20 nil)))

	   (it "should return the original list if the element is not found"
              (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	  	(should= (Cons. 10 (Cons. 30 (Cons. 10 nil)))  (delete 20 xx))))

	   (it "should remove the element if it is found in the list"
	      (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	         (should= (Cons. 30 (Cons. 10 nil)) (delete 10 xx)))))             


(describe "delete-all"
           (it "should work with empty lists"
	  	(should= nil (delete-all 20 nil)))

	   (it "should return the original list if the element is not found"
              (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	  	(should= (Cons. 10 (Cons. 30 (Cons. 10 nil))) (delete-all 20 xx))))

	   (it "should remove the elements if they are found in the list"
	      (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	         (should= (Cons. 30 nil) (delete-all 10 xx)))))
	


(describe "efficient-delete"
          (it "should work with empty lists"
	  	(should= nil (efficient-delete 20 nil)))

          (it "should return the original list if the element is not found"
	      (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	  	(should= true (identical? xx (efficient-delete 20 xx)))))
        
	   (it "should remove the element if it is found in the list"
	      (let [xx (Cons. 10 (Cons. 30 (Cons. 10 nil)))]
	         (should= (Cons. 30 (Cons. 10 nil)) (efficient-delete 10 xx))))) 

(run-specs)
