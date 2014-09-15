all: compilar run

# compila o codigo
compilar:
	javac *.java
# corre o codigo e apresenta output como pedido
run:
	java Menu
