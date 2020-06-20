CLASS_FOLDER = socialDistanceShopSampleSolution/
OUTDIR = bin/

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(CLASS_FOLDER)*.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) $(CLASS_FOLDER)*.class

run:
	java $(CLASS_FOLDER)SocialDistancingShop