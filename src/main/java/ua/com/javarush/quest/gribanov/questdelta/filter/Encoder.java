package ua.com.javarush.quest.gribanov.questdelta.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import lombok.SneakyThrows;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;

@WebFilter(value = AppURL.ALL_URL, initParams = @WebInitParam(name = "code", value = "UTF-8"))
public class Encoder implements Filter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("code");
    }


    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String encoding = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding(code);
        }

        encoding = response.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

}
