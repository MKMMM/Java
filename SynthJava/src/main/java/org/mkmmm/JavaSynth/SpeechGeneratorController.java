package org.mkmmm.JavaSynth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// This annotation indicates that the class serves as a web controller
@Controller
public class SpeechGeneratorController {

    private static final String SERVICE_REGION = "germanywestcentral";

    // This annotation gets the API key value from application properties
    @Value(value = "${api.key}")
    private String subscriptionKey;
    private String outputFilePath;

    // This method is called after bean construction to initialize any post-construction logic
    @PostConstruct
    public void initialize() {
        try {
            // Creating a temporary output file to store the generated speech in .wav format
            outputFilePath = Files.createTempFile("output", ".wav").toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // POST endpoint for generating speech from text
    @PostMapping("/generate-speech")
    public ResponseEntity<String> generateSpeech(@RequestBody String requestBody, @RequestParam("voice") String voice) {
        try {
            // Deserializing the input JSON payload to extract text content
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(requestBody);
            String text = jsonNode.get("text").asText();

            // Calling the method to convert the extracted text to speech
            synthesizeSpeech(text, voice);
            return new ResponseEntity<>("Speech generated successfully!", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Speech generation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET endpoint to retrieve and play the generated audio
    @GetMapping("/play-audio")
    public ResponseEntity<byte[]> playAudio() {
        try {
            // Reading the audio data from the generated file
            Path audioFilePath = Paths.get(outputFilePath);
            byte[] audioData = Files.readAllBytes(audioFilePath);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=output.wav")
                    .body(audioData);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Private method that uses Microsoft's Speech SDK to synthesize speech from text
    private void synthesizeSpeech(String text, String voice) throws Exception {
        // Configuring the SDK with the subscription key and region
        SpeechConfig config = SpeechConfig.fromSubscription(subscriptionKey, SERVICE_REGION);
        config.setSpeechSynthesisVoiceName(voice);

        // Setting the output to be the temporary file path created earlier
        AudioConfig audioConfig = AudioConfig.fromWavFileOutput(outputFilePath);
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(config, audioConfig);

        try {
            // Actual call to synthesize the speech
            SpeechSynthesisResult result = synthesizer.SpeakText(text);

            // Checking the result of the speech synthesis process
            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                System.out.println("Speech synthesis completed.");
            } else if (result.getReason() == ResultReason.Canceled) {
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                System.out.println("Speech synthesis canceled: " + cancellation.getReason().toString());
            }
        } finally {
            synthesizer.close();  // Ensure resources are freed after synthesis is complete
        }
    }
}
