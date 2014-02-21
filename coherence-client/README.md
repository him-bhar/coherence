While setting up client code in eclipse add project cache in build path of this project.

Now we have two things here:

1. Extend client
2. InvocableService client.

Extend client is basically a program which connects to the cluster via TCP/IP channel in client config

Following JVM variable needs to be before executing both clients: 

-Dtangosol.pof.enable=true 
-Dtangosol.pof.config=my-pof-config.xml 
-Dtangosol.coherence.cacheconfig=C:\Users\himanshu\Documents\GitHub\coherence\coherence-client\src\main\resources\client-cache-config.xml

Invocable service is something which resides inside the cache node itself and has a run method from AbstractInvocable. So when externally we trigger this service we can execute it on all nodes or only a specific node, using member set arg.