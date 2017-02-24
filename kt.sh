# import keytool
#sudo keytool -import -file ./crawlera-ca.crt -storepass changeit -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre/lib/security/cacerts -alias crawleracert

# standard test
#curl -vx proxy.crawlera.com:8010 -U 1bd66098acef4410b8eec210c15a1d68: http://httpbin.org/ip

#test pmcc
curl -vx proxy.crawlera.com:8010 -U 1bd66098acef4410b8eec210c15a1d68: http://pubmedcentralcanada.ca/pmcc/articles/PMC3000318/