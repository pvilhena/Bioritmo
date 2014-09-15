all: compilar run

# compila o codigo
compilar:
	javac src/*.java -d classes

# corre o codigo e apresenta output como pedido
run:
	CLASSPATH=classes:. java Menu

# apaga os ficheiros .class
clean:
	rm -rf classes/*.class output.txt