# JavaSynth: Speech Synthesis Application

JavaSynth is a web-based Java application designed to convert textual data into speech using Microsoft's Cognitive Speech Services.

## Core Features

- **Text to Speech Generation**: The main functionality of this application is to take text input and generate corresponding speech.
- **Play Generated Audio**: Once the speech is generated, it can be retrieved and played back.
- **Temporary Audio Storage**: Generated audio is stored temporarily in a `.wav` format for retrieval.

## Endpoints

1. **POST `/generate-speech`**: Accepts a JSON body with the text to be converted to speech and a voice parameter to determine the type of voice to be used for synthesis.

2. **GET `/play-audio`**: Retrieves the generated speech in `.wav` format.

## Key Components

- **SpeechGeneratorController**: This class serves as the web controller, providing the endpoints for the application.
  
- **SpeechConfig & SpeechSynthesizer**: Part of the Microsoft's Speech SDK used to handle speech synthesis.

- **AudioConfig**: Configures where the synthesized audio will be outputted.

- **ObjectMapper**: Part of the `com.fasterxml.jackson.databind` package, used for deserializing JSON payloads.

## Configuration

- **API Key**: The application requires an API key from Microsoft's Cognitive Speech Services. This key is specified in the application properties.

- **Service Region**: The region for the Speech Service, set to `germanywestcentral` in this application.

## Exception Handling

- Proper exception handling is implemented to ensure graceful error messages in case of failures, like when speech generation fails or if there's an issue reading the generated audio.

---
