import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Token;

import javax.swing.*;
import java.util.List;

import static java.lang.System.*;

public class Analyze {
    public static List<Token> analyzeSyntaxText(String text) throws Exception {
        LanguageServiceClient language = LanguageServiceClient.create();
        Document doc = Document.newBuilder()
                .setContent(text)
                .setType(Type.PLAIN_TEXT)
                .build();
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                .setDocument(doc)
                .setEncodingType(EncodingType.UTF16)
                .build();
        // analyze the syntax in the given text
        AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
        // print the response
        for (Token token : response.getTokensList()) {
            out.println("-------------");
            out.printf("\tTexto: %s\n", token.getText().getContent());
            String validar= String.valueOf(token.getPartOfSpeech().getTag());
            String gene = String.valueOf(token.getPartOfSpeech().getGender());
            String tem = String.valueOf(token.getPartOfSpeech().getTense());
            String num = String.valueOf(token.getPartOfSpeech().getNumber());
            String pers = String.valueOf(token.getPartOfSpeech().getPerson());
            if (validar=="DET"){
                out.println("Tipo: ARTICULO");
            }
            if (validar=="PRON"){
                out.println("Tipo: PRONOMBRE");
            }
            if (validar=="NOUN"){
                out.println("Tipo: SUSTANTIVO");
            }if (validar=="VERB"){
                out.println("Tipo: VERBO");
            }if (validar=="ADP"){
                out.println("Tipo: PREPOSICION");
            }if (validar=="NUM"){
                out.println("Tipo: NUMERO");
            }if (validar=="CONJ"){
                out.println("Tipo: CONJUNCION");
            }
            if (validar=="PRT"){
                out.println("Tipo: PARTICULA GRAMATICAL");
            }
            if (validar=="PUNCT"){
                out.println("Tipo: SIGNO DE PUNTUACION");
            }
            if (validar=="X"){
                out.println("Tipo: Desconocido");
            }


            if (gene=="FEMININE"){
                out.println("Genero: Femenino");
            }
            if (gene=="MASCULINE"){
                out.println("Genero: Masculino");
            }
            if (tem=="PAST"){
                out.println("Tiempo: Pasado");
            }
            if (tem=="FUTURE"){
                out.println("Tiempo: Futuro");
            }

            if (num=="SINGULAR"){
                out.println("Numero: Singular");
            }
            if (num=="PLURAL"){
                out.println("Numero: Plural");
            }

            if (pers=="FIRST"){
                out.println("Pronombre: Primera Persona");
            }
            if (pers=="SECOND"){
                out.println("Pronombre: Segunda Persona");
            }
            if (pers=="THIRD"){
                out.println("Pronombre: Tercera Persona");
            }



            /*System.out.printf("Lemma: %s\n", token.getLemma());*/
            /*System.out.printf("\tTiempo: %s\n", token.getPartOfSpeech().getTense());*/

        }
        return response.getTokensList();
        }

    public static void main(String[] args) {
        try {
            String palabra = JOptionPane.showInputDialog("Ingrese una Oracion");
            analyzeSyntaxText(palabra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}