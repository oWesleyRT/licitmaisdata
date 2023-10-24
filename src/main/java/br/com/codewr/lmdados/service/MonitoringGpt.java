package br.com.codewr.lmdados.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kotlin.jvm.internal.SerializedIr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MonitoringGpt {
    @Value("${logFileLocation}")
    private String logFileLocation;

    public void monitoring(String obj1, String obj2) {
        List<String> logList = new ArrayList<>();

        // Execute as operações com os objetos e registre os logs
        logList.add("Request: " + obj1);
        logList.add("Response: " + obj2);

        // Converter a lista de logs em JSON de forma legível
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonLog = "";
        try {
            jsonLog = objectMapper.writeValueAsString(logList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Salvar o JSON no arquivo no modo de anexação
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileLocation, true))) {
            writer.append(jsonLog).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
