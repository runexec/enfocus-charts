(ns enfocus.charts.test
  (:require [enfocus.core :as ef]
            [enfocus.effects :as effects]
            [enfocus.charts.core :as charts])
  (:require-macros [enfocus.macros :as em]))

(def pie-data [{:value 30 :label "time"}
               {:value 30 :label "pizza"}
               {:value 25 :label "freinds"}
               {:value 20 :label "country man"}
               {:value 10 :label "fore fathers"}
               {:value 10 :label "nice guys"}
               {:value 5  :label "dudes"}])

(def pie-options {:show-labels? true
                  :ease-fn effects/circular-out
                  :on-value-click #(js/alert (str (:label %) ": " (:value %)))})

(def line-data [{:label "Series1"
                 :values [30 12 95 40 50 30 35]}
                {:label "Series2"
                 :values [-10 50 40 70 25 35 45]}])
  
(def line-options {:tick-size 3
                   :scale-min -20
                   ;:scale-max 100 
                   :y-scale-title "Temperature"
                   :x-scale-title "Month"
                   :series-colors ["#cfcfcf" "#9f9fff"]
                   :categories ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul"]
                   :on-value-click #(js/alert (str (:series-label %) ": " (:value %)))})

(def stack-l-data [{:label "Series1"
                  :values [20 12 95 40 50 30 35]
                  :stack :new}
                {:label "Series2"
                 :values [10 50 40 70 25 35 45]
                 :stack :new}])

(def stack-l-options (assoc line-options :scale-min 0 :bezier-curve? false))
                
(def stack-b-data [{:label "Series1"
                  :values [20 12 95 40 50 30 35]
                  :stack :new}
                {:label "Series2"
                 :values [10 50 40 70 25 35 45]
                 :stack :new}
                {:label "Series3"
                 :values [5 55 30 70 55 45 25]
                 :stack :loner}])

(def stack-options (dissoc (dissoc line-options :series-colors) :scale-min))

(em/defaction draw-charts []
  "#pchart-div" (charts/pie-chart pie-data pie-options)
  "#lchart-div" (charts/line-chart line-data line-options)
  "#slchart-div" (charts/line-chart stack-l-data stack-l-options)
  "#bchart-div" (charts/bar-chart line-data line-options)
  "#sbchart-div" (charts/bar-chart stack-b-data stack-options))

(set! (.-onload js/window) draw-charts)
