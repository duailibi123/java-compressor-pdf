# Java PDF Compressor

Este é um projeto Java para comprimir arquivos PDF utilizando a biblioteca Aspose.PDF. O projeto usa o Maven para gerenciamento de dependências e construção do projeto.

Requisitos
Antes de executar o projeto, verifique se os seguintes requisitos estão atendidos:

Java 11 ou superior instalado.
Maven instalado.
Aspose.PDF versão 23.1 (a versão 23.1 deve ser baixada manualmente, conforme instruções abaixo).
1. Instalando o Java (Versão 11 ou superior)
Antes de começar, verifique se o Java 11 ou superior está instalado no seu sistema. Para isso, abra um terminal ou prompt de comando e execute:

bash
Copiar código
java -version
Se você receber uma mensagem indicando que o Java não está instalado, siga as instruções abaixo:

Para Windows:
Acesse o site oficial do Java SE Downloads.
Baixe e instale o JDK 11 para Windows.
Após a instalação, configure as variáveis de ambiente JAVA_HOME e adicione o caminho do binário Java (C:\Program Files\Java\jdk-11.x.x\bin) à variável de ambiente Path.
Para macOS:
Você pode usar o Homebrew para instalar o Java:
bash
Copiar código
brew install openjdk@11
Após a instalação, adicione o Java ao seu PATH:
bash
Copiar código
export PATH="/usr/local/opt/openjdk@11/bin:$PATH"
Para Linux (Ubuntu/Debian):
Instale o OpenJDK 11:
bash
Copiar código
sudo apt update
sudo apt install openjdk-11-jdk
Verifique a instalação com:
bash
Copiar código
java -version
2. Instalando o Maven
Depois de garantir que o Java está instalado corretamente, instale o Maven.

Para Windows:
Acesse o site oficial do Maven e baixe o arquivo Binary zip archive para Windows.
Extraia o conteúdo do arquivo ZIP em uma pasta, como C:\maven.
Adicione o caminho da pasta bin do Maven ao Path nas variáveis de ambiente:
C:\maven\apache-maven-3.x.x\bin (substitua 3.x.x pela versão correspondente).
Para macOS (usando Homebrew):
Se o Homebrew já estiver instalado, execute:
bash
Copiar código
brew install maven
Verifique a instalação com:
bash
Copiar código
mvn -v
Para Linux (Ubuntu/Debian):
Instale o Maven com o comando:
bash
Copiar código
sudo apt update
sudo apt install maven
Verifique a instalação com:
bash
Copiar código
mvn -v
3. Baixando o Repositório
Depois de instalar o Java e o Maven, você pode clonar o repositório do projeto para o seu sistema local. Execute o seguinte comando no terminal ou prompt de comando:

bash
Copiar código
git clone https://github.com/duailibi123/java-compressor-pdf.git
Isso criará uma cópia local do repositório do GitHub no seu computador.

4. Baixando a Biblioteca Aspose.PDF
A biblioteca Aspose.PDF versão 23.1 não está disponível diretamente no Maven Central, portanto, você precisa baixá-la manualmente:

Acesse a página de releases do Aspose: Aspose.PDF 23.1 Download

Baixe o arquivo .jar da versão 23.1.

Coloque o arquivo aspose-pdf-23.1.jar na pasta libs/ dentro do diretório raiz do projeto (java-compressor-pdf/).

5. Instalando a Biblioteca Localmente no Maven
Após colocar o arquivo aspose-pdf-23.1.jar na pasta libs/, você precisa instalá-lo no repositório local do Maven para que o Maven o reconheça e o use durante a construção do projeto.

Execute o seguinte comando para instalar o arquivo .jar manualmente no repositório local do Maven:

bash
Copiar código
mvn install:install-file -Dfile=libs/aspose-pdf-23.1.jar -DgroupId=com.aspose.pdf -DartifactId=aspose-pdf -Dversion=23.1 -Dpackaging=jar
6. Construindo e Executando o Projeto
Após instalar o Maven e configurar a biblioteca Aspose.PDF, você pode construir o projeto com os seguintes comandos:

## Compilar o projeto:

bash
Copiar código
mvn clean install
Executar o projeto:

bash
Copiar código
mvn exec:java
Isso irá executar a classe principal com.example.PDFCompressor, que realiza a compressão do PDF. Os arquivos de entrada e saída são especificados diretamente no código dentro da classe PDFCompressor.java.

## Observações:
O arquivo PDF de entrada deve estar localizado no caminho especificado no código: "C:\\Users\\<seu_nome_de_usuário>\\Desktop\\java-compressor\\java-compressor-pdf\\input\\input.pdf".

Nota: Substitua <seu_nome_de_usuário> pelo nome de usuário do seu sistema operacional (no exemplo acima, "marcos" é apenas um exemplo).

O arquivo PDF comprimido será gerado em: "C:\\Users\\<seu_nome_de_usuário>\\Desktop\\java-compressor\\java-compressor-pdf\\output\\output_compressed.pdf", com a compressão sendo aplicada até o número especificado de vezes.

Certifique-se de que o diretório libs/ contém o arquivo aspose-pdf-23.1.jar antes de executar o projeto.

## Como Funciona o Código:
O código principal está na classe PDFCompressor.java, localizada em src/main/java/com/example/PDFCompressor.java. Ela realiza a compressão do PDF múltiplas vezes, ajustando a qualidade das imagens a cada iteração, e exibe detalhes sobre o processo, como a velocidade de compressão e o percentual de redução do arquivo.

## Funcionalidade Principal:
Entrada: O código usa um arquivo PDF localizado no caminho definido (você pode modificar o caminho no código se necessário).
Compressão: O código usa o Aspose.PDF para otimizar os recursos do PDF, incluindo a compressão de imagens e remoção de objetos não utilizados.
Saída: O arquivo comprimido é salvo em um diretório de saída definido no código.
Contribuições:
Se você deseja contribuir para o projeto, sinta-se à vontade para enviar pull requests. Fique à vontade para melhorar a funcionalidade, corrigir bugs ou adicionar novos recursos.

Licença:
Este projeto está licenciado sob a MIT License.

## strutura do Projeto
graphql
Copiar código
java-compressor-pdf/
├── libs/
│   └── aspose-pdf-23.1.jar         # Biblioteca Aspose.PDF versão 23.1
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── PDFCompressor.java  # Código fonte da compressão de PDFs
├── input/
│   └── input.pdf                   # Arquivo PDF de entrada (exemplo)
├── output/
│   └── output_compressed.pdf       # Arquivo PDF comprimido (gerado após execução)
├── target/                         
│   ├── classes/
│   │   └── com/
│   │       └── example/
│   │           └── PDFCompressor.class  # Arquivo .class gerado após a compilação
│   ├── generated-sources/
│   │   └── annotations/            # Pasta de anotações geradas (vazia)
│   ├── generated-test-sources/
│   │   └── test-annotations/      # Pasta de anotações de teste (vazia)
│   ├── maven-archiver/
│   │   └── pom.properties          # Arquivo de propriedades do Maven
├── pom.xml                         # Arquivo de configuração do Maven
└── README.md                       # Este arquivo de documentação
