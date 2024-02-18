1. module.xml:
- location: jboss-eap-7.4.15/modules/org/example/testInfinispanCoreDependencyWithJgroupJar/main
- content:
[dmammare@dmammare main]$ ls
module.xml  testInfinispanCoreDependencyWithJgroupJar-1.0-SNAPSHOT.jar

2. standalone.xml:
it is the default standalone.xml, with the adding of:
<subsystem xmlns="urn:jboss:domain:ee:6.0">
    <global-modules>
        <module name="org.example.testInfinispanCoreDependencyWithJgroupJar"/>
        <module name="org.jgroups"/>
    </global-modules>
    ...
</subsystem>

Note: I add "org.jgroups" as global-modules because without that, loading:
http://127.0.0.1:8080/testInfinispanCoreDependencyWithJgroup/HelloWorld
I got an error of:
java.lang.NoClassDefFoundError: org/jgroups/nio/Buffers
And this is because I notice that "testInfinispanCoreDependencyWithJgroupJar-1.0-SNAPSHOT.jar" is a jar,
and not include in any libs folder the dependency of "org.infinispan.infinispan-core",
that will include org.jgroups.
The flow is:
testInfinispanCoreDependencyWithJgroup
\-> testInfinispanCoreDependencyWithJgroupJar (custom static module, added as global-module)
 \-> org.jgroups (added as global-module)

Output of the test:

Buffers package: Optional[file:/home/dmammare/redhat/sw/jboss/jboss-eap-7.4.15/modules/system/layers/base/org/jgroups/main/jgroups-4.2.23.Final-redhat-00001.jar!/]

So is using EAP jgroups module.