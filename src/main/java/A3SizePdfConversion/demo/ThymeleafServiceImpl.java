package A3SizePdfConversion.demo;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
public class ThymeleafServiceImpl implements ThymeleafService{

    private final TemplateEngine templateEngine;

    public ThymeleafServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    @Override
    public String createContent(String template, Map<String, Object> variables) {

        final Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(template,context);
    }
}
