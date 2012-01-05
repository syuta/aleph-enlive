(ns aleph-sample.core)
(use 'lamina.core 'aleph.http 'aleph.formats 'net.cgrand.enlive-html)


(defn get-template[path]
  (deftemplate html (apply str (rest path)) [title] [:title] (content title)))

(defn get-body [path]
  (apply str ((get-template path) "aleph-enlive sample"))) 


(defn app [channel request]
	(enqueue channel
    {:status 200
     :headers {"content-type" "text/html"}
     :body (get-body (request :uri))}))
 
(defn -main [& args]
  (start-http-server app {:port 8080}))