While starting the project on coherence. I was always facing the problem that coherence jars are not on maven. Stopping me to move ahead with development. Lots of googling led me to few good tips.

So the steps are to :

1. Download coherence archive from oracle site.
2. In <archive>/lib you will find coherence.jar, this is what we need to install in maven repo, extract this jar.
3. Goto command prompt and go to directory where this jar is now kept.
4. Use the following command to install it on maven.
    mvn install:install-file -Dfile=coherence.jar -DgroupId=com.oracle -DartifactId=oracle-coherence -Dversion=3.7.1 -Dpackaging=jar
Version can change depending upon the archive.

Download coherence archives from:

http://www.oracle.com/technetwork/middleware/coherence/downloads/coherence-archive-165749.html
Also can download the latest one.

Quick and simple…

Since (at the time of this writing at least) there is no public Oracle maven repository, but maven is so usefull while developing, these are the quick steps to install oracle coherence and Coherence Incubator projects to your local maven repository:

1) Inside the COHERENCE_HOME/lib directory, exec this command (replace the version to the one that fits you):

	mvn install:install-file -Dfile=coherence.jar -DgroupId=com.oracle -DartifactId=oracle-coherence -Dversion=3.7.0 -Dpackaging=jar
	
2) For each coherence incubator project you want to add (in this example I am adding the current coherence-common project), run this command (from the directory containing the Coherence Incubator Projects jars/zips):

	mvn install:install-file -Dfile=coherence-common-2.0.0.23647.jar -DgroupId=com.oracle -DartifactId=oracle-coherence-incubator-common -Dversion=2.0.0.23647 -Dsources=coherence-common-2.0.0.23647-src.zip -Dpackaging=jar
	
Done ;)

Storage and Non-Storage Nodes is defined by the following system property, while launching the cache:

For non-storage:

    -Dtangosol.coherence.distributed.localstorage=false  

For storage:

    -Dtangosol.coherence.distributed.localstorage=true  


Why would I need a non-storage node?
I might be interested in hosting the data cache separately than application. This way the cache will be launched as a storage node, and the application (which uses cache) will be launched as a non-storage node.