#!/usr/bin/python

### download_tweets.py ####
##
## Last update: 2013/06/17 (Inaki San Vicente)
##
##
## Parameters:
##           - file containing the tweet ids of the tweet to download. Format: TREC microblog task format - "statusId \t userId \t hash \n" 
##
## Output: standard output. Format is the same as the input with the tweet texts added at the end of each line. If the script is not able to retrieve the original tweet 'Not Available' will be returned.
##           "statusId \t userId \t hash \t tweet_text\n" 
##           "statusId \t userId \t hash \t 'Not Available'\n" 
##           ...   
## Call examples:
##
##    %python download_tweets.py tweetIds.txt     
##    %python download_tweets.py tweetIds.txt > outputFile.txt    
##

import sys
import urllib
import re
import json

from bs4 import BeautifulSoup

cache = {}

for line in open(sys.argv[1]):
	fields = line.rstrip('\n').split('\t')
	sid = fields[0]
	uid = fields[2]

	#url = 'http://twitter.com/%s/status/%s' % (uid, sid)
	#print "debug: "+uid+"  "+sid+"\n" 

        tweet = None
	text = "Not Available"
	if cache.has_key(sid):
		text = cache[sid]
		#print "debug1"+text+"\n"
	else:
                try:
			# get status page
                        f = urllib.urlopen("http://twitter.com/%s/status/%s" % (uid, sid))
                        # parse with Beautiful soup
                        html = f.read().replace("</html>", "") + "</html>"
                        soup = BeautifulSoup(html, "html.parser")
			#small elements contain the status ids
			small = soup.select("small > a")
			#p elements next to small elements have the tweet content
			p = soup.find_all("p", attrs={'class' : "js-tweet-text"})
			# search for the tweet with the correct status id.
                        for i in range(len(small)):
				#print small[i]
				regex=re.escape(sid)
				if re.search(regex,str(small[i])):
					text= p[i].get_text()										
					cache[sid]=text
					break;
				
                except Exception:
                        continue
		
	text = text.replace('\n', ' ',)
	text = re.sub(r'\s+', ' ', text)
        #print json.dumps(tweet, indent=2)
        print "\t".join(fields + [text]).encode('utf-8')
