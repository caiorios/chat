Chat
====

Chat feito em Java durante minha graduação para a matéria de Sistemas Distribuídos. Para a comunicação entre cliente e servidor foi utilizado RMI.

# Instalação

A construção do projeto é feita utilizando Maven. Em versões do NetBeans 6.7+ essa tecnologia já vem por padrão instalada. Como alternativa, em distribuições Debian, sua instalação pode ser feita executando o comando `sudo apt-get install maven`.

Após a instalação do Maven, na raiz do projeto, basta executar `mvn clean install`. Os .jars são mantidos na pasta /target dos projetos.

# Utilização

O projeto é composto por 2 partes, um cliente e um servidor. Primeiramente, é necessário inicializar o servidor, através do comando `java -jar chat-server-1.0.0.jar`. Então da mesma forma, basta executar o cliente através do comando `java -jar chat-client-1.0.0.jar`.
