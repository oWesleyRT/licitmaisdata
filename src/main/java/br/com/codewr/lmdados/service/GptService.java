package br.com.codewr.lmdados.service;

import br.com.codewr.lmdados.dto.all.GptResult;
import br.com.codewr.lmdados.dto.all.Topico;
import br.com.codewr.lmdados.dto.in.GptCategoriesDTOIn;
import br.com.codewr.lmdados.dto.out.GPTCategoriesDTOOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GptService {

    @Autowired
    MonitoringGpt monitoringGpt;

    private String TOKEN = "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";

    private String SYSTEM_TASK_MESSAGE = "Você é um assistente de linguagem que receberá uma lista de categorias de licitações e precisa encontrar quais são as possíveis categorias que possam atender a uma frase ou palavra que será informada.\n" +
            "Responda em formato JSON somente com uma matriz chamada data: [ aqui dentro apenas os codigos separados por virgula e ordenados com os que fazem mais sentido primeiro]\n" +
            "Se não encontrar nada relacionado, retorne vazio";

    public StringBuilder askCategories(String keyword, String topics){

        OpenAiService openAiService = new OpenAiService(TOKEN, Duration.ofSeconds(60));

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .temperature(0.8)
                .topP(0.5)
                .messages(List.of(
                        new ChatMessage("system", SYSTEM_TASK_MESSAGE),
                        new ChatMessage("user", String. format("Dado o produto " + keyword + ", identifique a categoria de licitação mais relevante para fornecimento. Categorias disponíveis: \n" + topics))
                )).build();
//                .messages(
//                        List.of(
//                                new ChatMessage("system", SYSTEM_TASK_MESSAGE),
//                                new ChatMessage("user", String.format("A palavra ou frase é: " + keyword + "\n.Aqui está a lista de códigos e categorias: \n" + topics)))).build();

        StringBuilder builder = new StringBuilder();
        openAiService.createChatCompletion(chatCompletionRequest)
                .getChoices().forEach(choice -> {
                    builder.append(choice.getMessage().getContent());
                });

        System.out.println("==================================== ;; ====================================");
        System.out.println("Request: ");
        System.out.println(chatCompletionRequest);
        System.out.println("---------------------------------------------------------------------------- ");
        System.out.println("Response: ");
        System.out.println(builder);
        System.out.println("==================================== ;; ====================================");

        monitoringGpt.monitoring(String.valueOf(chatCompletionRequest), String.valueOf(builder));

        return builder;
    }

    public Object resolve(GptCategoriesDTOIn gptCategoriesDTOIn) throws JsonProcessingException {

        List<Topico> topicos = gptCategoriesDTOIn.getTopicos();
        GPTCategoriesDTOOut result = new GPTCategoriesDTOOut();

        String stringTopics = topicos.stream()
                .map(topico -> topico.getCodigo() + ":" + topico.getNome())
                .collect(Collectors.joining("\n"));


        StringBuilder resultGPT= askCategories(gptCategoriesDTOIn.getPalavra(), stringTopics);

        ObjectMapper objectMapper = new ObjectMapper();
        GptResult gptResult = objectMapper.readValue(resultGPT.toString(), GptResult.class);

        List<Integer> codesGptResults = gptResult.getData();

        result.setTopicos(gptCategoriesDTOIn.getTopicos().stream()
                .filter(topico -> codesGptResults.contains(topico.getCodigo()))
                .toList());

        return result;
    }

}
