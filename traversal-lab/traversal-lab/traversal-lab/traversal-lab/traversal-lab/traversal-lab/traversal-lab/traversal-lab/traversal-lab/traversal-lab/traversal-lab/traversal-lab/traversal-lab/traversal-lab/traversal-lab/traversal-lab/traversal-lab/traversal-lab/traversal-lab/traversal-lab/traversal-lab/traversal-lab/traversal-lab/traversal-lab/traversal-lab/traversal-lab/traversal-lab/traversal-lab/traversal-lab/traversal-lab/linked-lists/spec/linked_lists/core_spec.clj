(ns linked_lists.core-spec
(:require [speclj.core :refer :all]
	  [linked_lists.core :refer :all])
(:import  [linked_lists.core Cons]))

(describe "The record declaration"
	(it "should create something"
	(should-not= nil (Cons. 10 20)))

	(it "should have a car"
	(should= 10 (:car (Cons. 10 20))))
	(it "should have a cdr"
	(should= 20 (:cdr (Cons. 10 20))))

	(it "should be chainable"
	(should= 40 (-> (Cons. 10 (Cons. 20 (Cons. 30 40))) :cdr :cdr :cdr))))

(describe "insert-at-beginning"
	(it "creates a cons cell"
	(should-not= nil (insert-at-beginning 10 nil)))
	(it "should work with empty lists"
	(should= (Cons. 10 nil) (insert-at-beginning 10 nil) ))
	(it "should work with lists that have data"
	(let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	(should= (Cons. 5 xx) (insert-at-beginning 5 xx) ))))

(describe "insert-at-end"
	(it "creates a cons cell"
	(should= (Cons. 10 nil) (insert-at-end 10 nil)))

	(it "should work with empty lists"
	(should= (Cons. 10 nil) (insert-at-end 10 nil)))

	(it "should work with lists that have data"
	(let [xx (Cons. 1 (Cons. 2 nil))]
	(should= (Cons. 1 (Cons. 2 (Cons. 4 nil))) (insert-at-end 4 xx))))
)

(describe "sorted insert"
	(it "should work with empty lists"
	(should= (Cons. 2 nil) (sorted-insert 2 nil)))
	
	(it "should insert in order"
	(let [xx (Cons. 1 (Cons. 3 (Cons. 5 (Cons. 7 nil))))]
	(should= (Cons. 0 (Cons. 1 (Cons. 3 (Cons. 5 (Cons. 7 nil))))) (sorted-insert 0 xx))
	(should= (Cons. 1 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))) (sorted-insert 4 xx))
	(should= (Cons. 1 (Cons. 3 (Cons. 5 (Cons. 7 (Cons. 9 nil))))) (sorted-insert 9 xx))))

	(it "should recycle the memory"
	(let [xxx (Cons. 1 (Cons. 3 (Cons. 5 nil)))]
	(should (identical? (:cdr (:cdr (sorted-insert 2 xxx))) (:cdr xxx)))
	(should (identical? (:cdr (:cdr (:cdr (sorted-insert 4 xxx)))) (:cdr (:cdr xxx)))))))

(describe "search"
	(it "should work with empty lists"
	(should= false (search 1 nil)))

	(it "should work with ordered lists"
	(let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))]
	(should= false (search 0 xx))
	(should= true (search 1 xx))
	(should= true (search 4 xx))
	(should= true (search 2 xx))))

	(it "should work with disordered lists"
	(let [xx (Cons. 5 (Cons. 2 (Cons. 3 nil)))]
	(should= false (search 0 xx))
	(should= true (search 3 xx))
	(should= true (search 5 xx))
	(should= true (search 2 xx))))
)

(describe "delete"
	(it "should work with empty lists"
	(should= nil (delete 4 nil)))

	(it "should delete one copy even some element is repeated"
	(let [xx (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 3 (Cons. 4 nil)))))]
	(should= (Cons. 2 (Cons. 3 (Cons. 3 (Cons. 4 nil)))) (delete 5 xx))
	(should= (Cons. 5 (Cons. 3 (Cons. 3 (Cons. 4 nil)))) (delete 2 xx))
	(should= (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 3 nil)))) (delete 4 xx))
	(should= (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (delete 3 xx))))

	(it "should not change the list if the element is not in the list"
	(let [xx (Cons. 1 (Cons. 2 nil))]
	(should= (Cons. 1 (Cons. 2 nil)) (delete 3 xx))))
)

(describe "delete-all"
	(it "shoud work with empty lists"
	(should= nil (delete 3 nil)))

	(it "should delete all copy"
	(let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 nil))))]
	(should= (Cons. 1 (Cons. 3 nil)) (delete-all 2 xx))
	(should= (Cons. 1 (Cons. 2 (Cons. 2 nil))) (delete-all 3 xx))
	(should= (Cons. 2 (Cons. 2 (Cons. 3 nil))) (delete-all 1 xx))))

	(it "should not change the list if the element is not in the list"
	(should= (Cons. 1 (Cons. 2 nil)) (delete-all 0 (Cons. 1 (Cons. 2 nil)))))
)

(describe "efficient-delete"
	(it "should return the original if the element is not in the list"
	(should= (Cons. 1 (Cons. 2 nil)) (efficient-delete 0 (Cons. 1 (Cons. 2 nil)))))

	(it "should work with empty lists"
	(should= nil (delete 4 nil)))

	(it "should delete one copy even some element is repeated"
	(let [xx (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 3 (Cons. 4 nil)))))]
	(should= (Cons. 2 (Cons. 3 (Cons. 3 (Cons. 4 nil)))) (delete 5 xx))
	(should= (Cons. 5 (Cons. 3 (Cons. 3 (Cons. 4 nil)))) (delete 2 xx))
	(should= (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 3 nil)))) (delete 4 xx))
	(should= (Cons. 5 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (delete 3 xx))))

	(it "should return the original if nothing is deleted"
	(let [xx (Cons. 1 (Cons. 2 nil))]
	(should (identical? xx (efficient-delete 0 xx)))))
)
(run-specs)


