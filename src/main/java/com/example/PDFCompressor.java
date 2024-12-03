package com.example;

import com.aspose.pdf.Document;
import com.aspose.pdf.optimization.OptimizationOptions;
import com.aspose.pdf.optimization.ImageCompressionOptions;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class PDFCompressor {
    public static void main(String[] args) {
        // Caminhos dos arquivos de entrada e saída
        String inputFilePath = "C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\input\\input.pdf";
        String outputFilePath = "C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\output\\output_compressed.pdf";

        // Número de vezes que a compressão deve ser feita
        int numberOfCompresses = 3;

        // Verificar se o arquivo de entrada existe
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            System.out.println("Erro: Arquivo de entrada nao encontrado em: " + inputFilePath);
            return;
        }
        System.out.println("Arquivo de entrada encontrado: " + inputFilePath);

        try {
            // Chamar a função para comprimir o arquivo várias vezes
            compressPDFMultipleTimes(inputFilePath, outputFilePath, numberOfCompresses);
        } catch (Exception e) {
            System.err.println("Erro ao comprimir o PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Função para comprimir um arquivo PDF dado várias vezes
    public static void compressPDFMultipleTimes(String inputFilePath, String outputFilePath, int numberOfCompresses) {
        String currentInputFilePath = inputFilePath;
        String currentOutputFilePath = outputFilePath;

        // A qualidade inicial das imagens
        double imageQuality = 15.0;  // Qualidade inicial de 10%

        int compressCount = 0;
        long previousFileSize = 0;  // Variável para armazenar o tamanho do arquivoF anterior
        long initialFileSize = new File(inputFilePath).length();  // Tamanho inicial do arquivo

        // Obter o horário inicial com o fuso horário do Amazonas (AMT)
        ZoneId amazonasZone = ZoneId.of("America/Manaus");  // Fuso horário do Amazonas (UTC -4)
        ZonedDateTime startTime = ZonedDateTime.now(amazonasZone);
        ZonedDateTime previousTime = startTime;  // Para calcular o tempo de cada iteração

        System.out.println("Inicio do processo de compressao: " + startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        // Variáveis para controlar a velocidade de compressão
        ZonedDateTime previousCompressTime = startTime; // Tempo da compressão anterior
        double compressionSpeedKBps = 0; // Inicializando a velocidade

        while (compressCount < numberOfCompresses) {
            try {
                // Inicializar as opções de otimização
                OptimizationOptions optimizationOptions = new OptimizationOptions();
                optimizationOptions.setRemoveUnusedStreams(true);  // Remover streams não utilizados
                optimizationOptions.setRemoveUnusedObjects(true); // Remover objetos não utilizados
                optimizationOptions.setUnembedFonts(true);        // Desincorporar fontes, se possível

                // Configurar compressão de imagens para a qualidade especificada
                ImageCompressionOptions imageCompressionOptions = optimizationOptions.getImageCompressionOptions();
                imageCompressionOptions.setCompressImages(true);  // Ativar compressão de imagens
                imageCompressionOptions.setImageQuality((int) imageQuality);  // Usar a qualidade atual das imagens

                // Carregar o documento PDF
                Document pdfDocument = new Document(currentInputFilePath);

                // Otimizar recursos do PDF
                pdfDocument.optimizeResources(optimizationOptions);

                // Gerar um nome de arquivo diferente para a próxima compressão
                File outputDirectory = new File("C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\output");
                if (!outputDirectory.exists()) {
                    outputDirectory.mkdirs(); // Criar o diretório de saída se não existir
                }

                // Atualizar o caminho de saída para incluir o número da compressão
                currentOutputFilePath = "C:\\Users\\marcos\\Desktop\\java-compressor\\hello-world\\output\\output_compressed_" + (compressCount + 1) + ".pdf";

                // Salvar o arquivo comprimido
                ZonedDateTime compressStartTime = ZonedDateTime.now(amazonasZone); // Iniciar o tempo de compressão
                pdfDocument.save(currentOutputFilePath);

                // Obter o tamanho do arquivo gerado
                File outputFile = new File(currentOutputFilePath);
                long currentFileSize = outputFile.length();

                // Calcular o tempo decorrido desde a compressão anterior
                ZonedDateTime compressEndTime = ZonedDateTime.now(amazonasZone);
                Duration compressDuration = Duration.between(compressStartTime, compressEndTime);

                // Calcular o tempo total decorrido até o momento
                Duration totalElapsedTime = Duration.between(startTime, compressEndTime);
                long totalSeconds = totalElapsedTime.getSeconds();  // Converter para segundos

                // Calcular a velocidade de compressão (em KB/s)
                if (compressCount > 0 && totalSeconds > 0) {
                    // Calcular a diferença de tamanho em KB
                    double sizeDifferenceKB = (previousFileSize - currentFileSize) / 1024.0;
                    compressionSpeedKBps = sizeDifferenceKB / totalSeconds;  // Velocidade em KB/s
                }

                // Verificar se o tamanho do arquivo gerado é igual ao do anterior
                if (currentFileSize == previousFileSize) {
                    // Se o tamanho não mudou, reduzir a qualidade das imagens em 0.3% e repetir a compressão sem incrementar o contador
                    imageQuality -= 0.3;
                    System.out.println("Compressao nao foi eficaz. Reduzindo a qualidade das imagens para " + imageQuality + "%");
                    continue; // Não incrementa compressCount, repete a compressão
                } else {
                    // Se o tamanho mudou, atualizar o tamanho do arquivo anterior e incrementar a compressão
                    previousFileSize = currentFileSize;
                }

                // Calcular o tempo decorrido desde o início
                ZonedDateTime currentTime = ZonedDateTime.now(amazonasZone);
                Duration elapsedTime = Duration.between(previousTime, currentTime);
                previousTime = currentTime;

                // Calcular e imprimir o percentual de compressão
                int percentual = (compressCount + 1) * 100 / numberOfCompresses;
                System.out.println("Compressao " + (compressCount + 1) + " de " + numberOfCompresses + " concluida. Percentual: " + percentual + "%");

                // Estimar o tempo restante
                Duration estimatedTimeRemaining = totalElapsedTime.multipliedBy(numberOfCompresses).dividedBy(compressCount + 1).minus(totalElapsedTime);

                // Exibir o tempo de duração, estimativas e velocidade de compressão
                System.out.println("Tempo decorrido nesta compressao: " + formatDuration(elapsedTime));
                System.out.println("Tempo estimado restante: " + formatDuration(estimatedTimeRemaining));
                System.out.println("Tempo total decorrido: " + formatDuration(totalElapsedTime));

                if (compressCount > 0) {
                    System.out.println("Velocidade de compressao: " + String.format("%.2f KB/s", compressionSpeedKBps));
                }

                // Preparar para a próxima compressão, usando o arquivo comprimido como entrada
                currentInputFilePath = currentOutputFilePath;

                // Incrementar o contador de compressões
                compressCount++;
            } catch (Exception e) {
                System.err.println("Erro ao comprimir o PDF na iteracao " + (compressCount + 1) + ": " + e.getMessage());
                e.printStackTrace();
                break; // Caso ocorra um erro, interrompe o processo
            }
        }

        // Ao final de todas as compressões
        System.out.println("Processo de compressao concluido!");

        // Obter o tamanho final do arquivo após as compressões
        long finalFileSize = new File(currentOutputFilePath).length();
        // Calcular a diferença de tamanho
        long sizeDifference = initialFileSize - finalFileSize;
        double sizeDifferenceMB = sizeDifference / 1024.0 / 1024.0; // Diferença em MB
        double compressionPercent = (double) sizeDifference / initialFileSize * 100;  // Percentual de compressão

        System.out.println("Tamanho final do arquivo comprimido: " + finalFileSize / 1024.0 / 1024.0 + " MB");
        System.out.println("Redução total de tamanho: " + String.format("%.2f", sizeDifferenceMB) + " MB");
        System.out.println("Percentual de compressao: " + String.format("%.2f", compressionPercent) + "%");
    }

    // Função auxiliar para formatar a duração (tempo)
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
