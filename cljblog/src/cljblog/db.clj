(ns cljblog.db
  (:require [monger.core :as mg]
            [monger.colletion :as mc]))

(def db-connection-uri (or (System/getenv "CLJ BLOG_MONGO_URI")
                            "mongodb://127.0.0.1/cljblog-test"))

(def db (-> db-connection-uri
            mg/connect-via-url
            :db))

(def articles-coll "articles")

(defn create-article [title body ]
  (mc/insert db articles-coll { :title title
                  :body body
                  :created (new java.util.Date)
                  }))

(defn list-articles []
  (mc/find-maps db articles-coll)) 
