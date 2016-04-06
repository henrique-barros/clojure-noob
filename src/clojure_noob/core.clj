(ns clojure-noob.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
(println "Cleanliness is next to godliness")

(defn tran
  []
  (println "Choo choo!"))

(defn error-messag
  [severity]
  (str "SOME ERROR MESSAGE"
       (if (= severity :mild)
         "mild" "doomed")))

(defn dec-maker
  [n]
  (fn
    [x]
    (loop [count n
           result x]
      (if (= count 0)
         result
         (recur
                (dec count)
                (dec result))))))

(defn dec-maker2
  [n]
  (fn
    [x]
    (- x n)))

(defn mapset
  [f vector]
  (set (map f vector)))

(defn add-part-number
  [part number]
  {:name (clojure.string/replace (:name part)
                                 (re-pattern (str "^" (str (- number 1)) "-"))
                                 (str (str number) "-"))
   :size (:size part)})

(defn matching-parts-number
  [part number]
  (loop [i 2
         parts [part]
         current part]
    (if (> i number)
      parts
      (let [current (add-part-number current i) first-part (first parts)]
        (recur (inc i) (into [] (set (into parts (set [current]))))
                  current)))))

(defn my-sum
  "Recursive implementation of sum"
  [x y]
  (let [sum (inc x)
  new-y (dec y)]
  (loop [new-value sum
          countdown new-y]
          (if (> countdown 0)
            (recur (inc new-value) (dec countdown))
            new-value))))

(defn new-sum
  [a b]
  (+ a b))
