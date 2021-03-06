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

(defn my-assoc-in
  "My implementation of assoc-in function"
  [varmap vararray varvalue]
  (let [head (first vararray)
    tail (rest vararray)]
    (if (empty? tail)
      (assoc varmap head varvalue)
      (assoc varmap head (my-assoc-in {} tail varvalue)))))

(defn my-update-in
  "My implementation of update-in function"
  [hash key func]
  (let [firstkey (first key)
    restkey (rest key)]
    (if (empty? restkey)
      (update hash firstkey func)
      (assoc hash firstkey (my-update-in (get hash firstkey) restkey func)))))

(def my-name (list 'read-string "(println \"Henrique Barros\")"))

(def my-name2 (list 'println "Henrique Barros"))

(defn infix
  "Function that takes a list in the format (x + y * z - w)
    and transforms it into a list that clojure can evaluate"
  [string]
  (list
    (nth string 5)
    (list
      (nth string 1)
      (nth string 0)
      (list
        (nth string 3)
        (nth string 2)
        (nth string 4)))
     (nth string 6)))

(defmacro when-valid
  "Macro that does a series of actions if the condition
  is true"
  [condition & actions]
  `(if ~condition (do ~@actions) nil))

(defmacro OR
  "implemenation of OR"
  ([] true)
  ([x] x)
  ([x & next]
    `(let [or# ~x]
      (if or# or#
        (OR ~@next)))))
