;; Program A
(defn fun-a [n]
  (if (< n 1) 10
      (* 2 (fun-a (- n 1)))))

;; Program B
(defn fun-b [n]
  (if (< n 1) 10
      (* 2 (fun-b (/ n 2)))))

;; Program C
(defn fun-c [n]
  (cond (< n 10)    (fun-c (+ n 1))
        (>= n 10)   (fun-c (+ n 2))))

;; Program D
(defn fun-d [n]
  (cond (< n 10)  10
        (>= n 10) 20
        :else     (fun-d (- n 2))))

;; Program for question 5
;; Pass 0 as the initial callcount
(defn collatz-len [n call-count]
  (+ call-count 1)
  (cond (= n 1)   call-count
        (= (mod n 2) 1) (collatz-len (+ (* n 3) 1) call-count))
        :else         (collatz-len (/ n 2) call-count))
;; example execution (collatz-len (56 0))


;; Questions
;; 1. Program C will never return a final result so therefore it will go into
;;    an infinite loop.
;; 
;; 2. Program A will run in linear (O(n)) time. 
;;
;; 3. Program B will run in logarithmic (O(log(n))) time.
;;
;; 4. Program D will never make a recursive call because the conditionals are
;;    such that the code will never reach the 'recursive part'.
;;
;; 5. Write a function that calculates the length of the Collatz sequence
;;    for a given input n. (If n is odd, multiply by three and add one. If n
;;    is even, divide by two. Stop when you reach 1, and return the number of
;;    calls made.)
;;      **See defined function collatz-len for answer**
