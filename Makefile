# Make sure JAVA_HOME is set
ifeq ($(origin JAVA_HOME), undefined)
	JAVA_HOME=/usr
endif

ifneq (,$(findstring CYGWIN,$(shell uname -s)))
	COLON=\;
	JAVA_HOME := `cygpath -up "$(JAVA_HOME)"`
else
	COLON=:
endif

SRCS=$(wildcard src/*.java)

binarystrings.jar binarystrings.jar.pack.gz: $(SRCS) NetLogoLite.jar Makefile manifest.txt
	mkdir -p classes
	$(JAVA_HOME)/bin/javac -g -encoding us-ascii -source 1.6 -target 1.6 -classpath NetLogoLite.jar:classes -d classes $(SRCS)
	jar cmf manifest.txt binarystrings.jar -C classes .
	pack200 --modification-time=latest --effort=9 --strip-debug --no-keep-file-order --unknown-attribute=strip binarystrings.jar.pack.gz binarystrings.jar


NetLogo.jar:
	curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/NetLogo.jar'

NetLogoLite.jar:
	curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/NetLogoLite.jar'


lib/NetLogo-tests.jar:
	mkdir -p lib
	(cd lib; curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/NetLogo-tests.jar')
lib/scalatest_2.9.2-1.8.jar:
	mkdir -p lib
	(cd lib; curl -O -f -s -S -L 'http://search.maven.org/remotecontent?filepath=org/scalatest/scalatest_2.9.2/1.8/scalatest_2.9.2-1.8.jar')

lib/scala-library.jar:
	mkdir -p lib
	(cd lib; curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/lib/scala-library.jar')
lib/picocontainer-2.13.6.jar:
	mkdir -p lib
	(cd lib; curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/lib/picocontainer-2.13.6.jar')
lib/asm-all-3.3.1.jar:
	mkdir -p lib
	(cd lib; curl -O -f -s -S 'http://ccl.northwestern.edu/netlogo/5.0.5/lib/asm-all-3.3.1.jar')

.PHONY: test
test: binarystrings.jar NetLogo.jar tests.txt lib/NetLogo-tests.jar lib/scalatest_2.9.2-1.8.jar lib/scala-library.jar lib/picocontainer-2.13.6.jar lib/asm-all-3.3.1.jar
	rm -rf tmp; mkdir tmp
	mkdir -p tmp/extensions/binarystrings
	cp binarystrings.jar tests.txt tmp/extensions/binarystrings
	(cd tmp; ln -s ../lib)
	(cd tmp; $(JAVA_HOME)/bin/java \
	  -classpath ../NetLogo.jar:../lib/scalatest_2.9.2-1.8.jar \
	  -Djava.awt.headless=true \
	  org.scalatest.tools.Runner -o \
	  -R ../lib/NetLogo-tests.jar \
	  -s org.nlogo.headless.TestExtensions)
