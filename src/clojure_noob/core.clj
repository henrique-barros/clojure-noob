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

(defn my-multiply
  "Recursive implementation of multiplication"
  [x y]
  (loop [total x
    timestoadd (dec y)]
    (if (> timestoadd 0)
      (recur (+ total x) (dec timestoadd))
      total)))

(defn my-integer-division
  "Very-naive recursive implementation of the integer division of two natural numbers"
  [x y]
  (let [start y]
    (if (> (* start y) x)
      (loop [new-start start]
        (if (<= (* new-start start) x)
          new-start
          (recur (dec new-start))))
      (loop [new-start start]
        (if (>= (* new-start start) x)
          (if (= (* new-start start) x) new-start (dec new-start))
          (recur (inc new-start)))))))



(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(defn attr
  "given attribute and character return the value"
  [char attrType]
  ((comp attrType :attributes) char))

(defn my-comp
  "Implementation of the comp function"
  [func1 func2]
  (fn [& args] (func1 (apply func2 args))))

(def new-c-int (my-comp :intelligence :attributes))
