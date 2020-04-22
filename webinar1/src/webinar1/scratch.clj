(ns webinar1.scratch
  (:require
   [tech.ml.dataset :as ds]
     [clojure.string :as string]
     [oz.core :as oz]
     [aerial.hanami.common :as hc]
     [aerial.hanami.templates :as ht]
     [aerial.hanami.core :as hmi]
     [clojure.pprint :as pp]
     [com.rpl.specter :as sp]
     [clojure.data.generators :as gen]))

(defonce oz (oz/start-server!))

;; https://www.kaggle.com/c/house-prices-advanced-regression-techniques

(defonce ames-raw-dataset
  (ds/->dataset "/workspace/data/ames/train.csv"))

(defn rng
  "Initialize and warm a ranrom number generator."
  [seed]
  (doto ^Random (java.util.Random. seed)
    (.nextDouble)))

(defn shuffle-with-seed
  "Shuffle data elements in a deterministic way."
  [seed data]
  (binding [gen/*rnd* (rng seed)]
    (gen/shuffle data)))

(def ames-dataset
  (->> ames-raw-dataset
       ds/column-names
       (map (fn [cn] [cn (keyword cn)]))
       (into {})
       (ds/rename-columns
        ames-raw-dataset)))

(def relevant-columns
  [:GrLivArea :SalePrice :BedroomAbvGr])

(def ames-rows
  (->> ames-dataset
       ds/mapseq-reader
       (shuffle-with-seed 1)
       (map (fn [row]
              (select-keys row relevant-columns)))
       (take 1000)))

(def plot1
  {:encoding
   {:y {:field :SalePrice, :type "quantitative"},
    :x {:field :GrLivArea, :type "quantitative"},
    :tooltip
    [{:field :GrLivArea, :type "quantitative"}
     {:field :SalePrice, :type "quantitative"}]},
   :mark {:type "circle"},
   :width 400,
   :background "floralwhite",
   :height 300
   :data {:values ames-rows}})

(def plot2
  (-> plot1
      (assoc-in [:encoding :color]
                {:field :BedroomAbvGr
                 :type  :nominal})
      (update-in [:encoding :tooltip]
                 conj {:field :BedroomAbvGr
                       :type  :nominal})))

(oz/view!
 [:div
  [:h1 "plot1"]
  [:vega-lite plot1]
  [:h1 "plot2"]
  [:vega-lite plot2]])
