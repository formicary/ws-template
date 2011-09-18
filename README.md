ws-template
===========

Example project of a web service with JAX-WS/JAXB, Apache CXF, and JPA/Hibernate.

JPA 2 and Weblogic
------------------

wls doesn't support JPA 2 out of the box, so we need to patch it, see here: http://download.oracle.com/docs/cd/E17904_01/web.1111/e13720/using_toplink.htm#EJBAD1309.

Find the commEnv.cmd/commEnv.sh script in WL_HOME/common/bin and add something like this:

set PRE_CLASSPATH=G:\kevin\projects\weblogic\modules\javax.persistence_1.0.0.0_2-0-0.jar;G:\kevin\projects\weblogic
\modules\com.oracle.jpa2support_1.0.0.0_2-0.jar


Eclipse
-------

The project files for eclipse (.project, .classpath, .settings/) are included and should allow you to import the project.  Before importing, make sure you have the Maven Integration for Eclipse plugin installed.  The Maven libs should(!) get included and updated automatically.  

The eclipse files should be kept up to date.  IDEA has an export feature, but I'm not sure that works so well, I think relying on the POM and manually making any other changes is probably more reliable.

Building the war from within eclipse can be done via a maven run config.  Deploying to weblogic is possible, but not configured yet.
