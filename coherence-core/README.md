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

Altered packaging structure for this project:

How will it help?
The new packaging gives us a complete deliverable. jar is present as is in target folder, but now the MANIFEST.MF is created in such a way that all required libraries are added in classpath as is.

"target/lib" directory is created in order to keep all third party dependencies.
from target folder execute:

    java -jar cache-xxx.jar
    
h3.There is a catch in starting multiple nodes on a single system.
 
Issue since these nodes are TcpAcceptor enabled, this can be viewed in the cache-config.xml of the cluster, under tags -  caching-schemes -> proxy-scheme. This is basically used to configure the TcpAcceptor, so that extend clients can join to the cluster.

    <autostart system-property="tangosol.coherence.extend.enabled">true</autostart>
 
This property inside proxy-scheme confirms if the node will have extend enabled or not.
Extend enabled means basically a TCP port open in cache node, so a client can connect to it via TCP/IP rather than joining the cluster.

Now, since a single host can support multiple nodes so it is necessary that only one node in a host is extend enabled.

h4.WHY?
This will be required else all nodes will try to start TcpAcceptor on the same port, which will lead to Bind Exception and except the first node, rest will fail to start.

h4.How to solve?
Simple, when starting the node which should be extend DISABLED, start it like:

    java -jar -Dtangosol.coherence.extend.enabled=false cache-xxx.jar
    
This will work perfectly fine.

In the logs of each node, we will see a difference only the node which will be extend enabled will show log messages like starting Tcp Acceptor