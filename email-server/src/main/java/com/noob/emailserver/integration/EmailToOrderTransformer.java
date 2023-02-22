package com.noob.emailserver.integration;

import com.noob.commons.model.Ingredient;
import com.noob.commons.model.Taco;
import com.noob.emailserver.model.EmailOrder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<EmailOrder> {
    private static final Logger log = LoggerFactory.getLogger(EmailToOrderTransformer.class);
    private static final String SUBJECT_KEYWORDS = "TACO ORDER";

    @Override
    protected AbstractIntegrationMessageBuilder<EmailOrder> doTransform(Message mailMessage) {
        EmailOrder tacoOrder = processPayload(mailMessage);
        return MessageBuilder.withPayload(tacoOrder);
    }

    private EmailOrder processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email =
                        ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException e) {
            log.error("MessagingException: {}", e.toString());
        } catch (IOException e) {
            log.error("IOException: {}", e.toString());
        }
        return null;
    }

    private EmailOrder parseEmailToOrder(String email, String content) {
        EmailOrder order = new EmailOrder(email);
        String[] lines = content.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().length() > 0 && line.contains(":")) {
                String[] lineSplit = line.split(":");
                String tacoName = lineSplit[0].trim();
                String ingredients = lineSplit[1].trim();
                String[] ingredientsSplit = ingredients.split(",");
                ArrayList<String> ingredientCodes = new ArrayList<>();
                for (String ingredientName : ingredientsSplit) {
                    String code = lookupIngredientCode(ingredientName.trim());
                    if (code != null) {
                        ingredientCodes.add(code);
                    }
                }
                Taco taco = new Taco(tacoName);
                taco.setIngredients(ingredientCodes);
                order.addTaco(taco);
            }
        }
        return order;
    }

    private String lookupIngredientCode(String ingredientName) {
        for (Ingredient ingredient : ALL_INGREDIENTS) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (LevenshteinDistance.getDefaultInstance()
                    .apply(ucIngredientName, ingredient.getName()) < 3 || ucIngredientName.contains(ingredient.getName()) ||
                    ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getCode();
            }
        }
        return null;
    }

    private static final Ingredient[] ALL_INGREDIENTS = new Ingredient[]{
            new Ingredient("FLTO", "FLOUR TORTILLA", Ingredient.Type.WRAP),
            new Ingredient("COTO", "CORN TORTILLA", Ingredient.Type.WRAP),
            new Ingredient("GRBF", "GROUND BEEF", Ingredient.Type.PROTEIN),
            new Ingredient("CARN", "CARNITAS", Ingredient.Type.PROTEIN),
            new Ingredient("TMTO", "TOMATOES", Ingredient.Type.VEGGIES),
            new Ingredient("LETC", "LETTUCE", Ingredient.Type.VEGGIES),

            new Ingredient("CHED", "CHEDDAR", Ingredient.Type.CHEESE),
            new Ingredient("JACK", "MONTERREY JACK", Ingredient.Type.CHEESE),
            new Ingredient("SLSA", "SALSA", Ingredient.Type.SAUCE),
            new Ingredient("SRCR", "SOUR CREAM", Ingredient.Type.SAUCE)
    };
}