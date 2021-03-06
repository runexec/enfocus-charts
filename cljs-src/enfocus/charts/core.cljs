(ns enfocus.charts.core
  (:require [enfocus.core :as ef]
            [enfocus.events :as events]
            [enfocus.effects :as effects]
            [enfocus.charts.pie :as pie]
            [enfocus.charts.line :as line]
            [goog.fx :as fx]))

(def defaults
  {:general {:font "Arial"
             :fill? true
             :fill-opacity 0.5
             :font-size 12
             :font-color "#3f3f3f"
             :padding 10
             :stroke? true
             :stroke-color "#3f3f3f"
             :stroke-width 1
             :on-value-mouseout nil
             :on-value-mouseover nil
             :on-value-click nil
             :animation? true
             :animation-duration 500
             :on-animation-complete nil
             :tooltip? true
             :tooltip-padding 5
             :tooltip-font-size 10
             :tooltip-font-color "#3f3f3f"
             :tooltip-background-color "#fff"
             :tooltip-opacity 0.7
             :tooltip-stroke-width 1
             :tooltip-stoke-color "#cfcfcf"
             :tooltip-hide-delay  1000
             :tooltip-content [["%s" :value]]}
   :pie {:chart-type :pie
         :padding nil
         :show-labels? false
         :font-color "#9f9f9f"
         :width 400
         :height 400
         :stroke-color "#fff"
         :slice-pop true
         :series-colors ["#F7464A" "#46BFBD" "#FDB45C" "#949FB1" "#4D5360"
                         "#7D4F6D" "#9D9B7F" "#D97041" "#584A5E"]
         :tooltip-content [["%s: %s" :label :value]]}
   :bar {:chart-type :bar
         :width 500
         :height 400
         :stroke-width 2
         :show-grid-lines? true
         :grid-color "#efefef"
         :tick-size 3
         :scale-title-font-size 15
         :scale-title-font-color "#3f3f3f"
         :series-colors ["#F7464A" "#46BFBD" "#FDB45C" "#949FB1" "#4D5360"
                         "#7D4F6D" "#9D9B7F" "#D97041" "#584A5E"]
         :tooltip-content [["%s" :category]
                           ["%s: %s" :series-label :value]]} 
   :line {:chart-type :line
          :width 500
          :height 400
          :bezier-curve? true
          :value-dot? true
          :dot-radius 3
          :stroke-width 2
          :dot-stroke-color "#fff"
          :y-scale-title nil 
          :x-scale-title nil 
          :show-grid-lines? true
          :grid-color "#efefef"
          :tick-size 3
          :scale-title-font-size 15
          :scale-title-font-color "#3f3f3f"
          :series-colors ["#F7464A" "#46BFBD" "#FDB45C" "#949FB1" "#4D5360"
                          "#7D4F6D" "#9D9B7F" "#D97041" "#584A5E"]
          :tooltip-content [["%s" :category]
                            ["%s: %s" :series-label :value]]}})

(defn get-defaults* [type]
    (merge (defaults :general) (defaults type)))

(def get-defaults (memoize get-defaults*))

(defn merge-options [options type]
  (merge (get-defaults type) options))

(defn pie-chart [data options]
  (let [config (merge-options options :pie)]
    (fn [node] (pie/pie-chart node data config))))           

(defn line-chart [data options]
  (let [config (merge-options options :line)]
    (fn [node] (line/series-chart node data config))))

(defn bar-chart [data options]
  (let [config (merge-options options :bar)]
    (fn [node] (line/series-chart node data config))))