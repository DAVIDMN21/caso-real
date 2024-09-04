// Definición del manejador abstracto
abstract class ContentHandler {
    protected ContentHandler nextHandler;

    public void setNextHandler(ContentHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleContent(String content);
}

// Manejador concreto para el Filtro Automático
class AutomatedFilter extends ContentHandler {
    @Override
    public void handleContent(String content) {
        if (content.contains("spam") || content.contains("offensive")) {
            System.out.println("AutomatedFilter: Content blocked due to inappropriate language.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleContent(content);
            }
        }
    }
}

// Manejador concreto para el Moderador Humano
class HumanModerator extends ContentHandler {
    @Override
    public void handleContent(String content) {
        if (content.contains("controversial topic")) {
            System.out.println("HumanModerator: Content flagged for review.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleContent(content);
            }
        }
    }
}

// Manejador concreto para el Comité de Revisión
class ReviewCommittee extends ContentHandler {
    @Override
    public void handleContent(String content) {
        System.out.println("ReviewCommittee: Final decision made on the content.");
    }
}

// Clase Cliente que inicia la cadena
public class SocialMediaModerationSystem {
    public static void main(String[] args) {
        // Crear los manejadores
        ContentHandler automatedFilter = new AutomatedFilter();
        ContentHandler humanModerator = new HumanModerator();
        ContentHandler reviewCommittee = new ReviewCommittee();

        // Configurar la cadena de responsabilidad
        automatedFilter.setNextHandler(humanModerator);
        humanModerator.setNextHandler(reviewCommittee);

        // Enviar contenido para revisión
        String content1 = "This post contains spam.";
        String content2 = "This post discusses a controversial topic.";
        String content3 = "This is a regular post.";

        System.out.println("Reviewing content1:");
        automatedFilter.handleContent(content1);

        System.out.println("\nReviewing content2:");
        automatedFilter.handleContent(content2);

        System.out.println("\nReviewing content3:");
        automatedFilter.handleContent(content3);
    }
}