(defproject webinar1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [metasoarous/oz "1.6.0-alpha6"]
                 [techascent/tech.ml.dataset "2.0-beta-23"]
                 [com.rpl/specter "1.1.3"]
                 ;; [scicloj/notespace "2.0.0-alpha4"]
                 ;; [cheshire "5.10.0"]
                 ;; [org.pinkgorilla/gorilla-plot "0.9.7"]
                 ;; [techascent/tech.viz "0.3"]
                 ;; [org.pinkgorilla/gorilla-notebook "0.4.16"]
                 [aerial.hanami "0.12.1"]
                 [org.clojure/data.generators "0.1.2"]]
  :repl-options {:init-ns webinar1.core})

