# Java PDF Compressor

Este é um projeto Java para comprimir arquivos PDF utilizando a biblioteca **Aspose.PDF**. O projeto usa o **Maven** para gerenciamento de dependências e construção do projeto.

## Requisitos

Antes de executar o projeto, verifique se os seguintes requisitos estão atendidos:

- **Java 11** ou superior instalado.
- **Maven** instalado.
- **Aspose.PDF versão 23.1** (a versão 23.1 deve ser baixada manualmente, conforme instruções abaixo).

## Estrutura do Projeto

A estrutura do projeto segue o padrão do Maven. Abaixo, a estrutura de diretórios:


## Passo 1: Baixando a Biblioteca Aspose.PDF

A biblioteca **Aspose.PDF versão 23.1** não está disponível diretamente no Maven Central, portanto, você precisa baixá-la manualmente:

1. Acesse a página de releases do Aspose:
   [Aspose.PDF 23.1 Download](https://releases.aspose.com/java/repo/com/aspose/aspose-pdf/23.1/)

2. Baixe o arquivo `.jar` para a versão 23.1.

3. Coloque o arquivo `aspose-pdf-23.1.jar` na pasta `libs/` dentro do diretório raiz do projeto (`java-compressor-pdf/`).

## Passo 2: Instalando a Biblioteca Localmente no Maven

Após colocar o arquivo `aspose-pdf-23.1.jar` na pasta `libs/`, você precisa instalá-lo no repositório local do Maven para que o Maven o reconheça e o use durante a construção do projeto.

Execute o seguinte comando para instalar o arquivo `.jar` manualmente no repositório local do Maven:

```bash
mvn install:install-file -Dfile=libs/aspose-pdf-23.1.jar -DgroupId=com.aspose.pdf -DartifactId=aspose-pdf -Dversion=23.1 -Dpackaging=jar

mvn clean install #para instalar as dependências do projeto

mvn exec:java #para executar o projeto

Isso irá executar a classe principal com.example.PDFCompressor, que vai realizar a compressão do PDF. Os arquivos de entrada e saída são especificados diretamente no código dentro da classe PDFCompressor.java.

Observações:
O arquivo PDF de entrada deve estar localizado no caminho especificado no código: "C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\input\\input.pdf".
O arquivo PDF comprimido será gerado em "C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\output\\output_compressed.pdf", com a compressão sendo aplicada até o número especificado de vezes.
Certifique-se de que o diretório libs/ contém o arquivo aspose-pdf-23.1.jar antes de executar o projeto.

Como Funciona o Código:
O código principal está na classe PDFCompressor.java, localizada em src/main/java/com/example/PDFCompressor.java. Ela realiza a compressão do PDF múltiplas vezes, ajustando a qualidade das imagens a cada iteração, e exibe detalhes sobre o processo, como a velocidade de compressão e o percentual de redução do arquivo.

Funcionalidade Principal:
Entrada: O código usa um arquivo PDF localizado no caminho definido (você pode modificar o caminho no código se necessário).
Compressão: O código usa o Aspose.PDF para otimizar os recursos do PDF, incluindo a compressão de imagens e remoção de objetos não utilizados.
Saída: O arquivo comprimido é salvo em um diretório de saída definido no código.

Contribuições:
Se você deseja contribuir para o projeto, sinta-se à vontade para enviar pull requests. Fique à vontade para melhorar a funcionalidade, corrigir bugs ou adicionar novos recursos.

Licença:
Este projeto está licenciado sob a MIT License.

Estrutura do projeto:

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
│   ├── maven-status/
│   │   └── maven-compiler-plugin/  # Pasta do plugin de compilação Maven (vazia)
│   ├── surefire-reports/
│   │   └── com.example.AppTest.txt # Relatório de testes do Maven Surefire
│   ├── test-classes/               # Pasta de classes de teste compiladas
│   ├── maven-status/
│   ├── java-compressor-pdf-1.0-SNAPSHOT.jar  # JAR final gerado após build
├── pom.xml                         # Arquivo de configuração do Maven
└── README.md                       # Este arquivo de documentação






